package com.awaliyah.ulvah.movie.movie.repository;

import com.awaliyah.ulvah.movie.movie.entity.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {

    @Query(value = "SELECT *  FROM PRODUCER WHERE WINNER = TRUE", nativeQuery = true)
    Optional<Producer> findProducerWinner();

}
