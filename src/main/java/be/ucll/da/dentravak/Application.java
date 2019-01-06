package be.ucll.da.dentravak;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichOrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    @Bean
    public CommandLineRunner demo(SandwichRepository sandwichRepository, SandwichOrderRepository sandwichOrderRepository) {
        return (args) -> {
//            // save a couple of sandwiches
            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Smos").withIngredients("Kaas,Hesp,Sla,Tomaat,Ei").withPrice(new BigDecimal("3.20")).build());
            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Martino").withIngredients("Américain,Ui,Augurk,Martinosaus").withPrice(new BigDecimal("3.20")).build());
//            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Ei en spek").withIngredients("Eieren,spek").withPrice(new BigDecimal("4.20")).build());
//            System.out.println(now);
//            sandwichOrderRepository.save(SandwichOrder.SandwichOrderBuilder.anOrder().withSandwichName("Smos").withBreadType(BreadTypeEnum.BOTERHAMMEKES).withMobilePhoneNumber("0498/157802").withCreationDate(LocalDateTime.now()).build());
//
//            UUID smosID = sandwichRepository.findSandwichByName("Smos").getId();
//            System.out.println("SMOS : " +smosID);
//            UUID martinoID = sandwichRepository.findSandwichByName("Martino").getId();
//            System.out.println("MARTINO : " +martinoID);
//            UUID eiID = sandwichRepository.findSandwichByName("Ei en spek").getId();
//            System.out.println("EI : " +eiID);
//
//
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("0498157802").withRatedItem(smosID).withRating(4.0f).build());
//
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("1").withRatedItem(smosID).withRating(5.0f).build());
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("1").withRatedItem(martinoID).withRating(5.0f).build());
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("1").withRatedItem(eiID).withRating(5.0f).build());
//
//
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("2").withRatedItem(smosID).withRating(1.0f).build());
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("2").withRatedItem(martinoID).withRating(5.0f).build());
//            recommendedItemRepository.save(RecommendedItem.RecommendedItemBuilder.aRecommendedItem().withEmail("1").withRatedItem(eiID).withRating(4.0f).build());
//
//
        };
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
        }
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}