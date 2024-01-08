from typing import Dict, Any

import requests
import pytest

from postgres_client import PostgresClient
from services.services_client import get_service_by_name
from services.services_dataset import Services, find_service_info_by_name
from services.services_init import insert_services


@pytest.fixture(scope='session')
def postgres():
    client = PostgresClient(
        host='localhost',
        port='5433',
        database_name='postgres',
        schema_name='public',
        user='postgres',
        password='password'
    )

    yield client
    client.close()


@pytest.fixture(scope='session')
def services(postgres: PostgresClient):
    return insert_services(postgres, 3)


@pytest.mark.parametrize('service_name', ['name_01', 'name_02'])
def test_services(services: Services, service_name: str):
    # given
    expected_services = find_service_info_by_name(services, service_name)

    # when
    result = get_service_by_name(service_name)

    assert result.status_code == requests.codes.ok
    body = result.json()
    for actual, expected in zip(body['content'], expected_services):
        assert_service(actual, expected)


def assert_service(actual_service: Dict[str, Any], expected_service: Dict[str, Any]):
    assert actual_service['name'] == expected_service['name']
    assert actual_service['price'] == expected_service['price']
    assert actual_service['length'] == expected_service['length']
    assert actual_service['car_size'] == expected_service['car_size']

