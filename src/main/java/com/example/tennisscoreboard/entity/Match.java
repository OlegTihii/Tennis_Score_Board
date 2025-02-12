package com.example.tennisscoreboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;

}
