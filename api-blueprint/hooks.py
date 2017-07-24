import dredd_hooks as hooks
import requests

@hooks.before("Serving > Serving > Serving")
def add_beer(transaction):
    requests.post(
        "http://localhost:8080/inventory/spirits",
        json = {
            'id': 'Tekillia',
            'type': 'tequila',
            'alcoholByVolume': 40,
            'proof': 80
        }
    )

    requests.post(
        "http://localhost:8080/inventory/wines",
        json = {
            'id': 'Wineo Pinot',
            'type': 'white',
            'style': 'pinot',
            'alcoholByVolume': 14
        }
    )