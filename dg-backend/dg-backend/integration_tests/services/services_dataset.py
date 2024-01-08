from typing import Any, Dict, List

from dataclasses import dataclass
import random


@dataclass
class Services:
    services: List[Dict[str, Any]]


def gen_services(services_number: int):
    car_size_list = ['MAŁE', 'ŚREDNIE', 'DUŻE']

    services = []
    for index in range(services_number):
        for size in car_size_list:
            services.append(gen_service(index, size))

    return services


def gen_service(index, car_size):
    lengths = ['01:00:00', '03:00:00', '05:00:00', '07:00:00', '09:00:00']

    return {
        'name': f'name_{index:02}',
        'price': random.randint(1, 5) * 200,
        'length': lengths[random.randint(0, 4)],
        'car_size': car_size
    }


def find_service_info_by_name(services, service_name):
    return [s for s in services if s['name'] == service_name]