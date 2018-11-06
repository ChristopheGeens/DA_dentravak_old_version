package be.ucll.da.dentravak.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String name;
    private List<Ingredient> ingredients;
    private double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static class SandwichBuilder{

        private String name;
        private List<Ingredient> ingredients;
        private double price;

        private SandwichBuilder(){}

        public static SandwichBuilder aSandwich(){
            return new SandwichBuilder();
        }

        //SETTERS (WITH ipv SET)
        public SandwichBuilder withName(String name){
            this.name = name; return this;
        }

        public SandwichBuilder withIngredients(List<Ingredient> ingredients){
            this.ingredients = ingredients; return this;
        }

        public SandwichBuilder withPrice(double price){
            this.price = price; return this;
        }

        //build
        public Sandwich build(){
            Sandwich sandwich = new Sandwich();
            //order.xxxxx = this.xxxxx;
            // ...
            sandwich.name = this.name;
            sandwich.ingredients = this.ingredients;
            sandwich.price = this.price;

            return sandwich;
        }
    }
}
