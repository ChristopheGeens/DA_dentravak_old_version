class AbstractDenTravakElement extends HTMLElement {
    connectedCall(){
        this.initShadowDom();
    }

    initShadowDom(){
        let shadowRoot = this.attachShadow({mode:'open'})
        shadowRoot.innerHTML = this.template;
    }

    get template(){
        throw "You did not define a Template";
    }

    byId(id){

    }
}