import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplaychampions();
});

function fetchAndDisplaychampions() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayChampions(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/champions', true);
    xhttp.send();
}

function displayChampions(champions) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    champions.champions.forEach(champion => {
        tableBody.appendChild(createTableRow(champion));
    })
}

function createTableRow(champion) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(champion["name"]));
    tr.appendChild(createLinkCell('view', '../champion_view/champion_view.html?champion=' + champion["name"]));
    tr.appendChild(createLinkCell('edit', '../champion_edit/champion_edit.html?champion=' + champion["name"]));
    tr.appendChild(createButtonCell('delete', () => deletechampion(champion)));
    return tr;
}

function deletechampion(champion) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaychampions();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/champions/' + champion["name"], true);
    xhttp.send();
}
