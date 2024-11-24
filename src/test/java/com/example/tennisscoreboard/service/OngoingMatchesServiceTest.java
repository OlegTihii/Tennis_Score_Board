package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.mapper.PlayerMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OngoingMatchesServiceTest {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Test
    void createMatch() {
        Player playerOne = new Player(1L, "PlayerOne");
        Player playerTwo = new Player(2L, "PlayerTwo");

        MatchDto matchDto = MatchDto.builder()
                .playerOne(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerOne))
                .playerTwo(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerTwo))
                .build();

        assertEquals("PlayerOne", matchDto.getPlayerOne().getName());
        assertEquals("PlayerTwo", matchDto.getPlayerTwo().getName());
        assertEquals(1L, matchDto.getPlayerOne().getId());
        assertEquals(2L, matchDto.getPlayerTwo().getId());
        assertEquals(0, matchDto.getPlayerOne().getGames());
        assertEquals(0, matchDto.getPlayerTwo().getGames());

    }
}