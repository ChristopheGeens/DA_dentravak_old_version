package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.repository.JpaJsonConverter;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class SandwichOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @Lob
    @Convert(converter = JpaJsonConverter.class)
    private List<OrderItem> orderItems;

    public SandwichOrder(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static class SandwichOrderBuilder{

        private List<OrderItem> orderItems = new ArrayList<OrderItem>();

        private SandwichOrderBuilder(){}

        public static SandwichOrderBuilder anOrder(){
            return new SandwichOrderBuilder();
        }

        //SETTERS (WITH ipv SET)
        public SandwichOrderBuilder withOrderItems(List<OrderItem> orderItems){
            this.orderItems = orderItems; return this;
        }

        //build
        public SandwichOrder build(){
            SandwichOrder sandwichOrder = new SandwichOrder();

            sandwichOrder.orderItems = this.orderItems;

            return sandwichOrder;
        }
    }
}
