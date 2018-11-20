class DenTravakSandwichList extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback(){
        this.initShadowDom();
        this.showSandwiches();
        this.innerHTML = this.template;
    }

    initShadowDom(){
        let shadowRoot = this.attachShadow('open');
        shadowRoot.innerHTML = this.template;
    }

    fetchAndShowSandwiches(){
        fetch('/api/sandwiches.json')
            .then(resp => resp.json())
            .then(sandwiches => this.showSandwiches(sandwiches))
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