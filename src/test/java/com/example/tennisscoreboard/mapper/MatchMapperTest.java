package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import com.example.tennisscoreboard.entity.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchMapperTest {

    @Test
    void testChoiceWinner1() {
        MatchDto matchDto = new MatchDto(
                PlayerMatchDto.builder()
                        .id(22L)
                        .playerName("Alex")
                        .sets(2)
                        .build(),
                PlayerMatchDto.builder()
                        .id(33L)
                        .playerName("Oleg")
                        .sets(1)
                        .build()
        );

        Match match = MatchMapper.INSTANCE.matchDtoToMatch(matchDto);

        assertEquals(22L, match.getPlayerOne().getId());
        assertEquals("Alex", match.getWinner().getName());
    }

    @Test
    void testChoiceWinner2() {
        MatchDto matchDto = new MatchDto(
                PlayerMatchDto.builder()
                        .id(22L)
                        .playerName("Alex")
                        .sets(1)
                        .build(),
                PlayerMatchDto.builder()
                        .id(33L)
                        .playerName("Oleg")
                        .sets(2)
                        .build()
        );

        Match match = MatchMapper.INSTANCE.matchDtoToMatch(matchDto);

        assertEquals(33L, match.getPlayerTwo().getId());
        assertEquals("Oleg", match.getWinner().getName());
    }

    @Test
    void testMapping() {
        MatchDto matchDto = new MatchDto(
                PlayerMatchDto.builder()
                        .id(22L)
                        .playerName("Mger")
                        .sets(2)
                        .build(),
                PlayerMatchDto.builder()
                        .id(33L)
                        .playerName("Igor")
                        .sets(1)
                        .build()
        );

        MatchWinnerDto matchWinnerDto = MatchMapper.INSTANCE.matchDtoToMatchWinnerDto(matchDto);
        assertEquals("Mger", matchWinnerDto.getPlayerOne());
    }
}
