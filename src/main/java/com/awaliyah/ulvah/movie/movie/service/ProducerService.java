package com.awaliyah.ulvah.movie.movie.service;

import com.awaliyah.ulvah.movie.movie.entity.Producer;
import com.awaliyah.ulvah.movie.movie.exception.ResourceNotFoundException;
import com.awaliyah.ulvah.movie.movie.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public Producer addNewProducer(Producer producer) {
        return this.producerRepository.save(producer);
    }

    public Producer updateProducer (Producer producer) {
        Producer retrievedProducer = this.producerRepository.findById(producer.getProducerId()).orElseThrow(
                () -> new ResourceNotFoundException("Producer Not Found")
        );


        return this.producerRepository.save(retrievedProducer);

    }

    public void deleteProducer(Long id) {

        Producer retrievedProducer = this.producerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producer not found.")
        );

        this.producerRepository.delete(retrievedProducer);
    }

    public Iterable<Producer> findAllProducers() {

        return this.producerRepository.findAll();
    }

    public Optional<Producer> findProducerWinner(){
        return this.producerRepository.findProducerWinner();
    }

    public void saveProducer(Producer producer) {
        this.producerRepository.save(producer);
    }
}
