package be.ucll.da.dentravak;

import be.ucll.da.dentravak.model.BreadTypeEnum;
import be.ucll.da.dentravak.model.SandwichOrder;
import be.ucll.da.dentravak.model.OrderItem;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichOrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(SandwichRepository sandwichRepository, SandwichOrderRepository sandwichOrderRepository) {
        return (args) -> {
            // save a couple of sandwiches
            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Smos").withIngredients("Kaas,Hesp,Sla,Tomaat,Ei").withPrice(new BigDecimal("3.20")).build());
            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Martino").withIngredients("Américain,Ui,Augurk,Martinosaus").withPrice(new BigDecimal("3.20")).build());
            OrderItem orderItem = OrderItem.OrderItemBuilder.anOrderItem().withBreadType(BreadTypeEnum.TURKISHBREAD).withSandwichName("Smos").withQuantity(2).withPrice(new BigDecimal("4.40")).build();
            OrderItem orderItem2 = OrderItem.OrderItemBuilder.anOrderItem().withBreadType(BreadTypeEnum.BOTERHAMMEKES).withSandwichName("Martino").withPrice(new BigDecimal("4.40")).withQuantity(1).build();
            //repository.save(Ingredient.IngredientBuilder.anIngredient().withName("Sla").build());
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            orderItems.add(orderItem);
            orderItems.add(orderItem2);
            sandwichOrderRepository.save(SandwichOrder.SandwichOrderBuilder.anOrder().withOrderItems(orderItems).build());
        };
    }

}