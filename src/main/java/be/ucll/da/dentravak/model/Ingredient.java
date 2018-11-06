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

        private String name;

        private IngredientBuilder(){}

        public static IngredientBuilder anIngredient(){
            return new IngredientBuilder();
        }

        //SETTERS (WITH ipv SET)
        public IngredientBuilder withName(String name){
            this.name = name; return this;
        }

        //build
        public Ingredient build(){
            Ingredient ingredient = new Ingredient();

            ingredient.name = this.name;

            return ingredient;
        }
    }
}
