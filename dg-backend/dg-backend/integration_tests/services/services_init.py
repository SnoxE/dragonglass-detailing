from postgres_client import PostgresClient
from services.services_dataset import gen_services


def insert_services(postgres: PostgresClient, services_number: int):
    services = gen_services(services_number)

    postgres.truncate('SERVICES', cascade=True)
    postgres.insert('SERVICES', [s for s in services])

    return services



