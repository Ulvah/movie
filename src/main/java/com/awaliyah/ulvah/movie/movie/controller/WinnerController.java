package com.awaliyah.ulvah.movie.movie.controller;

import com.awaliyah.ulvah.movie.movie.entity.Winner;
import com.awaliyah.ulvah.movie.movie.service.WinnerService;
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

@RestController
@RequestMapping(path="api/winner")
public class WinnerController {
    private final WinnerService winnerService;

    @Autowired
    public WinnerController(WinnerService winnerService){
        this.winnerService = winnerService;
    }

    @GetMapping
    public Iterable<Winner> getWinner(){

        return winnerService.findAllWinners();
    }

    @PostMapping
    public void registerNewWinner(@RequestBody Winner winner) {

        winnerService.addNewWinner(winner);
    }

    @DeleteMapping(path="{winnerId}")
    public void deleteProducer(@PathVariable("winnerId") Long winnerId) {
        winnerService.deleteWinner(winnerId);
    }

    @PutMapping(path="{winnerId}")
    public void updateProducer(
            @PathVariable("winnerId") Long id,
            @RequestParam(required = false) String producerName,
            @RequestParam(required = false) int previousWin,
            @RequestParam(required = false) int followingWin){

        Winner winnerUpdate = new Winner();
        winnerUpdate.setWinnerId(id);
        winnerUpdate.setProducer(producerName);
        winnerUpdate.setPreviousWin(previousWin);
        winnerUpdate.setFollowingWin(followingWin);
        winnerService.updateWinner(winnerUpdate);
    }
}
