package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SandwichPreferences;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class SandwichController {

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private SandwichRepository repository;

    @Inject
    private RestTemplate restTemplate;

    @RequestMapping("/sandwiches")
    public Iterable<Sandwich> sandwiches() {
        try {
            SandwichPreferences preferences = getPreferences("0498157802");
            //TODO: sort allSandwiches by float in preferences
            List<Map.Entry> entries = new ArrayList<>();
            for (Map.Entry<UUID,Float> entry : preferences.entrySet()){
                entries.add(entry);
            }

            entries.sort(Comparator.comparing(Map.Entry<UUID, Float>::getValue));

            List<Sandwich> sandwichesByRatings = new ArrayList<>();

            System.out.println(entries.size());



            try {
                if(!entries.isEmpty()){
                    for (Map.Entry<UUID,Float> entry: entries) {
                        System.out.println(repository.findById(UUID.fromString(entry.getKey().toString())).get().getName());
                        sandwichesByRatings.add(repository.findById(UUID.fromString(entry.getKey().toString())).get());
                    }
                }
            } catch (Exception e){}

            for(Sandwich sandwich: repository.findAll()){
                if(!sandwichesByRatings.contains(sandwich)){
                    sandwichesByRatings.add(sandwich);
                }
            }

            Collections.reverse(sandwichesByRatings);

            return sandwichesByRatings;

        } catch (ServiceUnavailableException e) {
            return repository.findAll();
        } catch (NullPointerException e) {
            return repository.findAll();
        }
    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    public Sandwich createSandwich(@RequestBody Sandwich sandwich) {
        return repository.save(sandwich);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.PUT)
    public Sandwich updateSandwich(@PathVariable UUID id, @RequestBody Sandwich sandwich) {
        if(!id.equals(sandwich.getId())) throw new IllegalArgumentException("Nownow, are you trying to hack us.");
        return repository.save(sandwich);
    }

    // why comment: for testing
    @GetMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommendation/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

//    public Optional<URI> recommendationServiceUrl() {
//        try {
//            return Optional.of(new URI("http://localhost:8081"));
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }
}