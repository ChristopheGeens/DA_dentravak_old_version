package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.repository.JpaJsonConverter;
//import org.springframework.data.annotation.Id;

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
    private List<OrderItem> orderItems;

    public Order(){

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

    public static class OrderBuilder{

        private List<OrderItem> orderItems = new ArrayList<OrderItem>();

        private OrderBuilder(){}

        public static OrderBuilder anOrder(){
            return new OrderBuilder();
        }

        //SETTERS (WITH ipv SET)
        public OrderBuilder withOrderItems(List<OrderItem> orderItems){
            this.orderItems = orderItems; return this;
        }

        //build
        public Order build(){
            Order order = new Order();

            order.orderItems = this.orderItems;

            return order;
        }
    }
}
