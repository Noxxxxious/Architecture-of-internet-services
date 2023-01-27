import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplaySkins();
});

function fetchAndDisplaySkins() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySkins(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/skins', true);
    xhttp.send();
}

function displaySkins(skins) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    skins.skins.forEach(skin => {
        tableBody.appendChild(createTableRow(skin));
    })
}

function createTableRow(skin) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(skin["name"]));
    tr.appendChild(createLinkCell('view', '../skin_view/skin_view.html?skin=' + skin["name"]));
    tr.appendChild(createLinkCell('edit', '../skin_edit/skin_edit.html?skin=' + skin["name"]));
    tr.appendChild(createButtonCell('delete', () => deleteSkin(skin)));
    return tr;
}

function deleteSkin(skin) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySkins();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/skins/' + skin["name"], true);
    xhttp.send();
}
