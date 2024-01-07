import requests


def authenticate(email: str, password: str) -> str:
    try:
        data_to_send = {
            "email": email,
            "password": password
        }

        response = requests.post('http://localhost:8080/api/token', json=data_to_send)
        if response.status_code == 200:
            data = response.json()
            return data['token']
        else:
            print("Failed to retrieve token:", response.status_code)
    except requests.exceptions.RequestException as e:
        print("Error: ", e)


def get_user():
    token = authenticate("snoxe@gmail.com", "password")
    headers = {"Authorization": f"Bearer {token}"}
    url = 'http://localhost:8080/api/users/user'

    try:
        response = requests.get(url=url, headers=headers)
        if response.status_code == 200:
            data = response.json()
            print(data)
        else:
            print("Failed to retrieve data:", response.status_code)
    except requests.exceptions.RequestException as e:
        print("Error: ", e)


def assert_user(actual_user: dict, expected_user):
    assert actual_user['id'] == expected_user['id']


if __name__ == '__main__':
    get_user()

