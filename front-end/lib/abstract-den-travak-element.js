export default class AbstractDenTravakElement extends HTMLElement {
    connectedCall(){
        this.initShadowDom();
    }

    initShadowDom(){
        if (this.shadowRoot) return;
        let shadowRoot = this.attachShadow({mode:'open'})
        shadowRoot.innerHTML = this.template;
        shadowRoot.insertBefore(document.getElementById('styletemplate').content.cloneNode(true), shadowRoot.childNodes[0]);
    }

    app() {
        return document.querySelector('dt-app');
    }

    get template(){
        throw "You did not define a Template";
    }

    byId(id){
        return this.shadowRoot.querySelector('#${id}');
    }
}