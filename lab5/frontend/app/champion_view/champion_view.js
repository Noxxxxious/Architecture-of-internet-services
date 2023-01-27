import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    setLinkNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayChampion();
    fetchAndDisplaySkins();
});

function fetchAndDisplaySkins() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySkins(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/champions/' + getParameterByName('champion') + '/skins', true);
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
    tr.appendChild(createTextCell(skin.name));
    tr.appendChild(createLinkCell('view', '../skin_view/skin_view.html?champion='
        + getParameterByName('champion') + '&skin=' + skin.name));
    tr.appendChild(createLinkCell('edit', '../skin_edit/skin_edit.html?champion='
        + getParameterByName('champion') + '&skin=' + skin.name));
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

function fetchAndDisplayChampion() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayChampion(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/champions/' + getParameterByName('champion'), true);
    xhttp.send();
}

function displayChampion(champion) {
    setTextNode('name', champion.name);
    setTextNode('priceBE', champion.priceBE);

    setLinkNode('add', 'ADD skin', "../skin_add/skin_add.html?champion=" + getParameterByName('champion'));
}
