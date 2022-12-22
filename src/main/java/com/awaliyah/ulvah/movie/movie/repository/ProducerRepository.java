package com.awaliyah.ulvah.movie.movie.repository;

import com.awaliyah.ulvah.movie.movie.entity.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {

    @Query(value = "SELECT p FROM PRODUCER p WHERE p.winner = true", nativeQuery = true)
    Optional<Producer> findProducerWinner();

}
