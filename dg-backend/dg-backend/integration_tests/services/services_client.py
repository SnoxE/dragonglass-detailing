import requests


def get_service_by_name(service_name):
    url = f'http://localhost:8080/api/services/{service_name}'

    try:
        return requests.get(url=url)
    except requests.exceptions.RequestException as e:
        print("Error: ", e)
