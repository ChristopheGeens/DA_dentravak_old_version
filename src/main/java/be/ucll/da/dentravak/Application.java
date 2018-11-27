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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            sandwichOrderRepository.save(SandwichOrder.SandwichOrderBuilder.anOrder()
                    .withSandwichName("Smoske")
                    .withBreadType(BreadTypeEnum.BOTERHAMMEKES)
                    .withMobilePhoneNumber("0474776686")
                    //.withCreationDate(LocalDateTime.now())
                    .build());
        };
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
        }
    }

}