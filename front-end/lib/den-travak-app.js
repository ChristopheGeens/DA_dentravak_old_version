import AbstractDenTravakElement from "./abstract-den-travak-element";

class DenTravakSandwichList extends AbstractDenTravakElement {

    connectedCallback(){
        super.connectedCallback
        fetch('../api/sandwiches.json')
            .then(resp => resp.json())
            .then(json => this.showSandwiches(json));
    }

    showSandwiches(sandwiches){
        let sandwichesUl = this.shadowRoot.querySelector('#sandwiches')
        sandwichesUl.innerHTML='';
        sandwiches.forEach(sandwich => {
            let sandwichLi = document.createElement('li');
            sandwichesUl.appendChild(sandwichLi);
            sandwichLi.innerHTML = sandwich.name;
        });
    }

    get template(){
        return '<ul id="sandwiches"><li>Hier komt een lijst met de sandwiches</li></ul>';
    }
}

customElements.define('dt-app', DenTravakSandwichList);