package com.awaliyah.ulvah.movie.movie.repository;

import com.awaliyah.ulvah.movie.movie.entity.Winner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface WinnerRepository extends PagingAndSortingRepository<Winner, Long> {
}
