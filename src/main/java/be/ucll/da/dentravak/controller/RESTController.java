package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.BreadTypeEnum;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.model.OrderItem;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.OrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RESTController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SandwichRepository sandwichRepository;

    public RESTController(OrderRepository orderRepository, SandwichRepository sandwichRepository){
        this.orderRepository = orderRepository;
        this.sandwichRepository = sandwichRepository;
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/orders")
    public List<Order> getOrders() {
//        Sandwich sandwich = Sandwich.SandwichBuilder.aSandwich().withName("Smos").withIngredients(getIngredients()).withPrice(2.20).build();
//        Order order = Order.OrderBuilder.anOrder().withSandwiches(Arrays.asList(sandwich)).build();
        List<OrderItem> items = new ArrayList<OrderItem>();
        items.add(OrderItem.OrderItemBuilder.anOrderItem().withBreadType(BreadTypeEnum.TURKISHBREAD).withSandwichName("Martino").withQuantity(4).withPrice(new BigDecimal("12.80")).build());
        items.add(OrderItem.OrderItemBuilder.anOrderItem().withBreadType(BreadTypeEnum.WRAP).withSandwichName("Smos").withQuantity(2).withPrice(new BigDecimal("6.40")).build());
        Order order = Order.OrderBuilder.anOrder().withOrderItems(items).build();
        return Arrays.asList(order);
    }

    @RequestMapping("/ingredients")
    public List<String> getIngredients() {

//            return StreamSupport.stream(ingrdientRepository.findAll().spliterator(),false)
//                    .map(ingredient -> (ingredient.setName("")))
//                    .collect(Collectors.toList());


//        Ingredient sla = Ingredient.IngredientBuilder.anIngredient().withName("Sla").build();
//        Ingredient tomaat = Ingredient.IngredientBuilder.anIngredient().withName("Tomaat").build();
        return Arrays.asList(null);
    }

    @RequestMapping("/sandwiches")
    public List<Sandwich> getSandwiches() {
        return (List<Sandwich>) StreamSupport.stream(sandwichRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    public void saveSandwiches(@RequestBody Sandwich sandwich) {
        sandwichRepository.save(sandwich);

    }
}
