package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RESTController {

    @RestController
    public class GreetingController {

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();

        @RequestMapping("/orders")
        public List<Order> getOrders() {
            Order order = Order.OrderBuilder.anOrder().withSandwiches(null).build();
            return Arrays.asList(order);
        }
    }
}
