import {getParameterByName, setTextNode} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaychampion();
});

function fetchAndDisplaychampion() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                    setTextNode(key, value);
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/champions/' + getParameterByName('champion'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaychampion();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/champions/' + getParameterByName('champion'), true);

    const request = {
        'priceBE': parseInt(document.getElementById('priceBE').value),
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}