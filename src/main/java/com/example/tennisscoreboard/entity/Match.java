package com.example.tennisscoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (!id.equals(match.id)) return false;
        if (!player1.equals(match.player1)) return false;
        if (!player2.equals(match.player2)) return false;
        return winner.equals(match.winner);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + player1.hashCode();
        result = 31 * result + player2.hashCode();
        result = 31 * result + winner.hashCode();
        return result;
    }
}
