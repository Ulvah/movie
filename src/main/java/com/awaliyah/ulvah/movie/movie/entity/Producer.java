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
public class Producer {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    Long producerId;
    Integer ano;
    String title;
    String studio;
    String producer;
    Boolean winner;

    public Producer(Integer ano, String title, String studio, String producerName, Boolean winner) {
        this.ano = ano;
        this.title = title;
        this.studio = studio;
        this.producer = producerName;
        this.winner = winner;
    }

}
