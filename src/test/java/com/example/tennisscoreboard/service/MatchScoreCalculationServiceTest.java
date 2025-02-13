package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchScoreCalculationServiceTest {

    private MatchScoreCalculationService matchScoreCalculationService;
    private MatchDto matchDto;
    private PlayerMatchDto playerOne;
    private PlayerMatchDto playerTwo;


    @BeforeEach
    public void beforeAll() {
        matchScoreCalculationService = new MatchScoreCalculationService();
        playerOne = PlayerMatchDto.builder().id(1L).playerName("Player_1").build();
        playerTwo = PlayerMatchDto.builder().id(2L).playerName("Player_2").build();

        matchDto = new MatchDto(playerOne, playerTwo);
    }


    @Test
    void givenDeuce_whenPlayerOneScores_thenGameNotOver() {
        playerOne.setPoints(3);
        playerTwo.setPoints(3);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(4, playerOne.getPoints());
        assertEquals(3, playerTwo.getPoints());
    }


    @Test
    void givenFortyLove_whenPlayerOneScores_thenPlayerOneWinsGame() {
        playerOne.setPoints(3);
        playerTwo.setPoints(0);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(0, playerOne.getPoints());

        assertEquals(0, playerTwo.getPoints());

        assertEquals(1, playerOne.getGames());
    }

    @Test
    void givenSixSixInGames_whenPlayerOneScores_thenTiebreakStarts() {
        playerOne.setGames(6);
        playerTwo.setGames(6);

        playerOne.setPoints(5);
        playerTwo.setPoints(4);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());

        assertEquals(7, playerOne.getGames());
        assertEquals(6, playerTwo.getGames());
        assertEquals(0, playerOne.getSets());
    }

    @Test
    void givenSixSixInGames_whenPlayerOneScores_thenTiebreakStarts_2() {
        playerOne.setGames(7);
        playerTwo.setGames(6);

        playerOne.setPoints(5);
        playerTwo.setPoints(4);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());
        assertEquals(1, playerOne.getSets());
    }

    @Test
    void givenSixSixInGames_whenPlayerOneScores_thenTiebreakStarts_3() {
        playerOne.setGames(5);
        playerTwo.setGames(5);

        playerOne.setPoints(5);
        playerTwo.setPoints(4);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());

        assertEquals(6, playerOne.getGames());
        assertEquals(5, playerTwo.getGames());
        assertEquals(0, playerOne.getSets());
    }

    @Test
    void givenSixSixInGames_whenPlayerOneScores_thenTiebreakStarts_4() {
        playerOne.setGames(6);
        playerTwo.setGames(6);

        playerOne.setPoints(1);
        playerTwo.setPoints(0);

        matchScoreCalculationService.updateScore(matchDto, playerOne.getId());

        assertEquals(2, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());

        assertEquals(6, playerOne.getGames());
        assertEquals(6, playerTwo.getGames());
        assertEquals(0, playerOne.getSets());
    }
}
