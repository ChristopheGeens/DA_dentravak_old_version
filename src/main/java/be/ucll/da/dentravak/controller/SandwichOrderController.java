package be.ucll.da.dentravak.controller;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SandwichOrder;
import be.ucll.da.dentravak.repository.SandwichOrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SandwichOrderController {

    private SandwichOrderRepository repository;
    private SandwichRepository sandwichRepository;

    public SandwichOrderController(SandwichOrderRepository repository,SandwichRepository sandwichRepository) {
        this.repository = repository;
        this.sandwichRepository = sandwichRepository;
    }

    @RequestMapping("/orders")
    public Iterable<SandwichOrder> orders() {
        return repository.findAll();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public SandwichOrder createSandwichOrder(@RequestBody SandwichOrder sandwichOrder) {
        sandwichOrder.setCreationDate(LocalDateTime.now());
        sandwichOrder.setPrice(sandwichRepository.findById(sandwichOrder.getSandwichId()).get().getPrice());
        return repository.save(sandwichOrder);
    }


}