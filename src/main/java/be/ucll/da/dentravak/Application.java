package be.ucll.da.dentravak;

import be.ucll.da.dentravak.model.Ingredient;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repository.IngredientRepository;
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
    public CommandLineRunner demo(IngredientRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(Ingredient.IngredientBuilder.anIngredient().withName("Sla").build());

        };
    }

}