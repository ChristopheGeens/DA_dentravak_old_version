package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.Ingredient;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RESTController {

    private OrderRepository orderRepository;

        public RESTController(OrderRepository orderRepository){
            this.orderRepository = orderRepository;
        }

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();

        @RequestMapping("/orders")
        public List<Order> getOrders() {
            Sandwich sandwich = Sandwich.SandwichBuilder.aSandwich().withName("Smos").withIngredients(getIngredients()).withPrice(2.20).build();
            Order order = Order.OrderBuilder.anOrder().withSandwiches(Arrays.asList(sandwich)).build();
            return Arrays.asList(order);
        }

        @RequestMapping("/ingredients")
        public List<Ingredient> getIngredients() {

//            return StreamSupport.stream(ingrdientRepository.findAll().spliterator(),false)
//                    .map(ingredient -> (ingredient.setName("")))
//                    .collect(Collectors.toList());


            Ingredient sla = Ingredient.IngredientBuilder.anIngredient().withName("Sla").build();
            Ingredient tomaat = Ingredient.IngredientBuilder.anIngredient().withName("Tomaat").build();
            return Arrays.asList(sla, tomaat);
        }
}
