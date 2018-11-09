package be.ucll.da.dentravak.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Sandwich {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private String ingredients;
    private BigDecimal price;

    public Sandwich(){

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class SandwichBuilder{

        private String name;
        private String ingredients;
        private BigDecimal price;

        private SandwichBuilder(){}

        public static SandwichBuilder aSandwich(){
            return new SandwichBuilder();
        }

        //SETTERS (WITH ipv SET)
        public SandwichBuilder withName(String name){
            this.name = name; return this;
        }

        public SandwichBuilder withIngredients(String ingredients){
            this.ingredients = ingredients; return this;
        }

        public SandwichBuilder withPrice(BigDecimal price){
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
