import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySkin();
});

function fetchAndDisplaySkin() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    if(getParameterByName('champion')) {
        xhttp.open("GET", getBackendUrl() + '/api/champions/' + getParameterByName('champion') + '/skins/' + getParameterByName('skin'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/skins/' + getParameterByName('skin'))
    }
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySkin();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/champions/' + getParameterByName('champion') + '/skins/'
        + getParameterByName('skin'), true);

    const request = {
        'name': document.getElementById('name').value,
        'priceRP': parseInt(document.getElementById('priceRP').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}