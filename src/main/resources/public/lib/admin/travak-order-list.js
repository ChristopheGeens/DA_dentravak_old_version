import DenTravakAbstractElement from '../travak-abstract-element.js';

class DenTravakOrderList extends DenTravakAbstractElement {

    constructor() {
        super('travak-admin-app')
    }

    connectedCallback() {
        super.connectedCallback();
        fetch('http://localhost:8080/den-travak/orders')
            .then(resp => resp.json())
            .then(json => this.updateOrderList(json));
        this.initEventListeners();
    }

    initEventListeners() {
        this.byId('edit-sandwiches-btn').addEventListener('click', (e) => this.app().showSandwichList());
        this.byId('print-orders-btn').addEventListener('click', (e) => this.printOrders());
    }

    updateOrderList(orders) {
        let orderList = this.byId('orders');
        orderList.innerHTML = ``;
        orders.forEach(order => {
            let orderEl = htmlToElement(this.getOrderTemplate(order));
            orderList.appendChild(orderEl);
        });
    }

    printOrders() {
        fetch("http://localhost:8080/den-travak/ordersPrint/", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        })
            //.then(data=>{return data.json()})
            //.then(res=>{console.log(res)})
            .then(function(response) {
                response.text().then(function (text) {
                        var pom = document.createElement('a');
                        var csvContent=text; //here we load our csv data
                        var blob = new Blob([csvContent],{type: 'text/csv;charset=utf-8;'});
                        var url = URL.createObjectURL(blob);
                        pom.href = url;
                        pom.setAttribute('download', 'orders.csv');
                        pom.click();
                        //var encodedUri = encodeURI(csvContent + text);
                        //window.open(encodedUri, "_blank", 'width=400,height=200');
                    })
            })
        this.app().dispatchEvent(new CustomEvent('print-succeeded', {detail: ""}));
    }

    get template() {
        return `
            <style>
                div.dt-order-info {
                    margin-left: auto;
                }
                .bmd-list-group-col {
                    width: 70%;
                }
                p.list-group-item-heading {
                    display:flex;
                    justify-content: space-between;
                }
                span.creationDate {
                    display:inline-block;
                    float: right;
                }
                .travak-header {
                    display: flex;
                }
                .travak-header button {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <div class="travak-header">
                    <h4>Den Travak Bestellingen</h4>
                    <button id="edit-sandwiches-btn" type="button" class="btn btn-primary">Bewerk broodjeslijst</button>
                    <button id="print-orders-btn" type="button" class="btn btn-primary">Print bestellingen</button>
                </div>
                <div>
                <ul id="orders" class="list-group">
                </ul>
                </div>
            </div>
        `;
    }

    getOrderTemplate(order) {
        return `
            <a class="list-group-item">
                <button type="button" class="btn btn-primary bmd-btn-fab">
                    ${order.name.charAt(0)}
                </button>
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">${order.mobilePhoneNumber}<span class="creationDate">${dateFns.distanceInWordsToNow(order.creationDate)} ago</span></p>
                    <p class="list-group-item-text">${order.name} - ${order.breadType.toLowerCase()}</p>
                </div>
                <div class="dt-order-info">
                    <p class="list-group-item-text">${order.price}</p>
                </div>
            </a>
        `;
    }
}

customElements.define('travak-order-list', DenTravakOrderList);