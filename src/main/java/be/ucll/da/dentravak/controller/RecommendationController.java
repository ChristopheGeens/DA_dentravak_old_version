package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.Item;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SlopeOne;
import be.ucll.da.dentravak.model.User;
import be.ucll.da.dentravak.repository.RecommendedItemRepository;

import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.StreamSupport;


@RestController
public class RecommendationController {

    private RecommendedItemRepository repository;

    @Autowired
    private SandwichRepository sandwichRepository;

    public RecommendationController(RecommendedItemRepository repository, SandwichRepository sandwichRepository) {
        this.repository = repository;
        this.sandwichRepository= sandwichRepository;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public RecommendedItem recommendItem(@RequestBody RecommendedItem recommendedItem) {
        return repository.save(recommendedItem);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/recommend/{emailAddress}")
    public Map<Item, Float> getRecommendedItems(@PathVariable String emailAddress) {
        List<RecommendedItem> recommendedItemsByEmailAddress = repository.findAllByEmailAddress(emailAddress);

        if(recommendedItemsByEmailAddress.isEmpty()) {
            return null;
        }

        SlopeOne slopeOnePredictionMachine = getSlopeOnePredictionMachine();
        Map<Item, Float> userPrefences = mapToSlopeOneInput(recommendedItemsByEmailAddress);

        return slopeOnePredictionMachine.predict(userPrefences);
    }

    private Map<Item, Float> mapToSlopeOneInput(List<RecommendedItem> recommendedItemsByEmailAddress) {
        HashMap<Item, Float> userPreferences = new HashMap<>();

        for (RecommendedItem recommendedItem : recommendedItemsByEmailAddress) {
            userPreferences.put(new Item(recommendedItem.getRatedItem()), recommendedItem.getRating());
        }
        return userPreferences;
    }

    private SlopeOne getSlopeOnePredictionMachine() {
        Map<User, Map<Item, Float>> allSavedPreferences = new HashMap<>();
        StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .forEach(item -> addRecommendedItemToAllSavedPreferences(allSavedPreferences, item));

        return new SlopeOne(allSavedPreferences);
    }

    private void addRecommendedItemToAllSavedPreferences(Map<User, Map<Item, Float>> allSavedPreferences, RecommendedItem item) {
        User user = new User(item.getEmailAddress());
        if(!allSavedPreferences.containsKey(user)) {
            allSavedPreferences.put(user, new HashMap<>());
        }

        Map<Item, Float> userPrefences = allSavedPreferences.get(user);
        userPrefences.put(new Item(item.getRatedItem()), item.getRating());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/sandwichesByRecommended", method = RequestMethod.GET)
    public List<Sandwich> sandwichesByRecommendation() {


        List<RecommendedItem> recommendedItemsByEmailAddress = repository.findAllByEmailAddress("0498157802");
        Map<Item, Float> userPrefences = mapToSlopeOneInput(recommendedItemsByEmailAddress);

        SlopeOne slopeOnePredictionMachine = getSlopeOnePredictionMachine();
        Map<Item,Float> ratings = slopeOnePredictionMachine.predict(userPrefences);

        System.out.println(ratings);

        List<Map.Entry> entries = new ArrayList<>();
        for (Map.Entry<Item,Float> entry : ratings.entrySet()){
            entries.add(entry);
        }

        entries.sort(Comparator.comparing(Map.Entry<Item, Float>::getValue));

        System.out.println(entries);

        List<Sandwich> sandwichesByRatings = new ArrayList<>();

        for (Map.Entry<Item,Float> entry: entries) {
            sandwichesByRatings.add(sandwichRepository.findById(UUID.fromString(entry.getKey().toString())).get());
        }


//        List<Item> items = new ArrayList(ratings.keySet());
//        items.forEach(System.out::println);
//
//        List<Float> itemRatings = new ArrayList(ratings.values());
//        itemRatings.forEach(System.out::println);



        Collections.reverse(sandwichesByRatings);

        return sandwichesByRatings;
    }
}
