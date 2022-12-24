package com.awaliyah.ulvah.movie.movie.controller;


import com.awaliyah.ulvah.movie.movie.entity.Producer;
import com.awaliyah.ulvah.movie.movie.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(path="api/producer")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService){
        this.producerService = producerService;
    }

    @GetMapping
    public Iterable<Producer> getProducers(){

        return producerService.findAllProducers();
    }

    @GetMapping(path="/winner")
    public Optional<Producer> getProducersWinner(){

        return producerService.findProducerWinner();
    }

    @PostMapping
    public void registerNewProducer(@RequestBody Producer producer) {
        producerService.addNewProducer(producer);
    }

    @DeleteMapping(path="{producerId}")
    public void deleteProducer(@PathVariable("producerId") Long producerId) {
        producerService.deleteProducer(producerId);
    }

    @PutMapping(path="{producerId}")
    public void updateProducer(
            @PathVariable("producerId") Long id,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String studio,
            @RequestParam(required = false) String producerName,
            @RequestParam(required = false) Boolean winner){

        Producer producerUpdate = new Producer();
        producerUpdate.setProducerId(id);
        producerUpdate.setAno(ano);
        producerUpdate.setTitle(title);
        producerUpdate.setStudio(studio);
        producerUpdate.setProducer(producerName);
        producerUpdate.setWinner(winner);

        producerService.updateProducer(producerUpdate);
    }
}
