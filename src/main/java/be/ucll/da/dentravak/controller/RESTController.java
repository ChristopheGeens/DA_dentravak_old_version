package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.SandwichOrder;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichOrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RESTController {

    @Autowired
    private SandwichOrderRepository sandwichOrderRepository;
    @Autowired
    private SandwichRepository sandwichRepository;

    public RESTController(SandwichOrderRepository sandwichOrderRepository, SandwichRepository sandwichRepository){
        this.sandwichOrderRepository = sandwichOrderRepository;
        this.sandwichRepository = sandwichRepository;
    }

    @RequestMapping("/orders")
    public List<SandwichOrder> getOrders() {
        return (List<SandwichOrder>) sandwichOrderRepository.findAll();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public SandwichOrder addSandwichOrder(@RequestBody SandwichOrder sandwichOrder){
        sandwichOrderRepository.save(sandwichOrder);
        return sandwichOrder;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Optional<SandwichOrder> showSandwichOrder(@PathVariable UUID id){
        return sandwichOrderRepository.findById(id);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public void deleteSandwichOrder(@PathVariable UUID id){
        sandwichOrderRepository.deleteById(id);
    }

    @RequestMapping("/ingredients")
    public List<String> getIngredients() {
        return Arrays.asList(null);
    }

    @RequestMapping("/sandwiches")
    public List<Sandwich> getSandwiches() {
        return (List<Sandwich>) StreamSupport.stream(sandwichRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    public Sandwich addSandwich(@RequestBody Sandwich sandwich) {
        sandwichRepository.save(sandwich);
        return sandwich;
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.GET)
    public Optional<Sandwich> showSandwich(@PathVariable UUID id) {
        return sandwichRepository.findById(id);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.PUT)
    public Sandwich editSandwich(@PathVariable UUID id, @RequestBody Sandwich sandwich) {
        if(id.equals(sandwich.getId())){
            sandwichRepository.save(sandwich);
        }
        return sandwich;
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.DELETE)
    public void deleteSandwich(@PathVariable UUID id) {
        sandwichRepository.deleteById(id);
    }

}
