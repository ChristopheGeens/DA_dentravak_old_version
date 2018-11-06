package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.repository.JpaJsonConverter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @Lob
    @Convert(converter = JpaJsonConverter.class)
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
