import {
    getParameterByName,
    setLinkNode,
    setTextNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplaySkin();
});

function fetchAndDisplaySkin() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySkin(JSON.parse(this.responseText))
        }
    };
    if(getParameterByName('champion')) {
        xhttp.open("GET", getBackendUrl() + '/api/champions/' + getParameterByName('champion') + '/skins/' + getParameterByName('skin'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/skins/' + getParameterByName('skin'))
    }
    xhttp.send();
}

function displaySkin(skin) {
    setTextNode('name', skin.name);
    setTextNode('priceRP', skin.priceRP);
    setTextNode('champion', skin.champion)
    setLinkNode('edit', 'edit', '../skin_edit/skin_edit.html?champion=' + getParameterByName('champion') + '&skin=' + skin.name)
}
