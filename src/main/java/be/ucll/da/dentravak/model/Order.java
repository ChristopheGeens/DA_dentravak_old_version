package be.ucll.da.dentravak.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;

    public Order(){

    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public static class OrderBuilder{

        private List<Sandwich> sandwiches = new ArrayList<Sandwich>();

        private OrderBuilder(){}

        public static OrderBuilder anOrder(){
            return new OrderBuilder();
        }

        //SETTERS (WITH ipv SET)
        public OrderBuilder withSandwiches(List<Sandwich> sandwiches){
            this.sandwiches = sandwiches; return this;
        }

        //build
        public Order build(){
            Order order = new Order();

            order.sandwiches = this.sandwiches;

            return order;
        }
    }
}
