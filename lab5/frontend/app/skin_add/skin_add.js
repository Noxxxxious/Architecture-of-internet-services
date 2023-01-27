import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    UpdateChampionField();
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            location.replace("../champion_view/champion_view.html?champion=" + getParameterByName("champion"));
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/skins/')

    const request = {
        'name': document.getElementById('name').value,
        'priceRP': parseInt(document.getElementById('priceRP').value),
        'champion': getParameterByName("champion")
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function UpdateChampionField() {
    document.getElementById('champion').value = getParameterByName("champion");
}