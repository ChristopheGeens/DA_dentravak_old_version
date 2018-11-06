package be.ucll.da.dentravak;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(OrderRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Order()); //TODO add orders with builder

        };
    }

}