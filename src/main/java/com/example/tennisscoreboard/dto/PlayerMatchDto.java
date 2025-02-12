package com.example.tennisscoreboard.dto;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlayerMatchDto {
    private Long id;
    private String playerName;

    private int sets; //todo почему если хардкодить счет, то он не появляется?
    private int games;
    private int points;

    public void wonSet() {
        sets++;
    }

    public void wonGame() {
        games++;
    }

    public void wonPoint() {
        points++;
    }

    public void cleanGames() {
        games = 0;
    }

    public void cleanPoints() {
        points = 0;
    }

}
