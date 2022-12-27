package com.awaliyah.ulvah.movie.movie.service;

import com.awaliyah.ulvah.movie.movie.entity.Winner;
import com.awaliyah.ulvah.movie.movie.exception.ResourceNotFoundException;
import com.awaliyah.ulvah.movie.movie.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerService {

    private final WinnerRepository winnerRepository;

    @Autowired
    public WinnerService(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    public Winner addNewWinner(Winner winner) {
        return this.winnerRepository.save(winner);
    }

    public Winner updateWinner (Winner winner) {
        Winner retrievedWinner = this.winnerRepository.findById(winner.getWinnerId()).orElseThrow(
                () -> new ResourceNotFoundException("Winner Not Found")
        );
        return this.winnerRepository.save(retrievedWinner);

    }

    public void deleteWinner(Long id) {

        Winner retrievedWinner = this.winnerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Winner not found.")
        );

        this.winnerRepository.delete(retrievedWinner);
    }

    public Iterable<Winner> findAllWinners() {

        return this.winnerRepository.findAll();
    }

    public void saveWinner(Winner winner) {
        this.winnerRepository.save(winner);
    }
}
