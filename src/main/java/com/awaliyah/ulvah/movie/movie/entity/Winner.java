package com.awaliyah.ulvah.movie.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Winner {

    @Id
    @SequenceGenerator(
            name = "winner_sequence",
            sequenceName = "winner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "winner_sequence"
    )
    Long winnerId;
    String producer;
    Integer previousWin;
    Integer followingWin;

    public Winner(String producerName, Integer previousWin, Integer followingWin) {
        this.producer = producerName;
        this.previousWin = previousWin;
        this.followingWin = followingWin;

    }


}
