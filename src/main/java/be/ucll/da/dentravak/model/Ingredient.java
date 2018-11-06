package be.ucll.da.dentravak.model;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class IngredientBuilder{

        private List<Sandwich> sandwiches = new ArrayList<Sandwich>();

        private IngredientBuilder(){}

        public static IngredientBuilder anOrder(){
            return new IngredientBuilder();
        }

        //SETTERS (WITH ipv SET)
        public IngredientBuilder withSandwiches(List<Sandwich> sandwiches){
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
