package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;

public class MatchScoreCalculationService {


    public void updateScore(MatchDto matchDto, Long id) {
        PlayerMatchDto playerOne = matchDto.getPlayerOne();
        PlayerMatchDto playerTwo = matchDto.getPlayerTwo();

        //todo Если мы посылаем ЛЮБОЙ id который не равен первому, он автоматом засчитывает очко второму.
        if (playerOne.getId().equals(id)) {
            calculateGamePoints(playerOne, playerTwo);
        } else {
            calculateGamePoints(playerTwo, playerOne);
        }
    }

    private void calculateGamePoints(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        pointWinner.wonPoint();

        if (isTiebreakSituation(pointWinner, looser)) {
            tiebreakSituation(pointWinner, looser);

        } else if (pointWinner.getPoints() > 3 && looser.getPoints() < 3) {
            pointWinner.wonGame();
            clearPoints(pointWinner, looser);
            calculateSetGames(pointWinner, looser);

        } else if (pointWinner.getPoints() > 2 && looser.getPoints() > 2) {
            deuceSituation(pointWinner, looser);
        }
    }

    private void calculateSetGames(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        if (pointWinner.getGames() > 5 && looser.getGames() < 6) {
            pointWinner.wonSet();
            clearGames(pointWinner, looser);

        }
    }

    private void deuceSituation(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        if (pointWinner.getPoints() - looser.getPoints() > 1) {
            pointWinner.wonGame();
            clearPoints(pointWinner, looser);
            calculateSetGames(pointWinner, looser);
        }
    }

    private boolean isTiebreakSituation(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        return pointWinner.getGames() == 6 && looser.getGames() == 6;
    }

    private void tiebreakSituation(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        if (pointWinner.getPoints() >= 6 && pointWinner.getPoints() - looser.getPoints() > 1) {
            pointWinner.wonSet();
            clearPoints(pointWinner, looser);
            clearGames(pointWinner, looser);
        }
    }

    private void clearPoints(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        pointWinner.cleanPoints();
        looser.cleanPoints();
    }

    private void clearGames(PlayerMatchDto pointWinner, PlayerMatchDto looser) {
        pointWinner.cleanGames();
        looser.cleanGames();
    }
}
