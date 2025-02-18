package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FinishedMatchesPersistenceServiceTest {

    @Test
    void testSave() {
        MatchRepository matchRepositoryMock = mock(MatchRepository.class);
        FinishedMatchesPersistenceService matchesPersistenceService = new FinishedMatchesPersistenceService(matchRepositoryMock);

        Player player_1 = new Player(1L, "One");
        Player player_2 = new Player(1L, "Two");


        PlayerMatchDto playerOne = PlayerMatchDto.builder().id(1L).playerName("playerOne").build();
        PlayerMatchDto playerTwo = PlayerMatchDto.builder().id(2L).playerName("playerTwo").build();

        MatchDto matchDto = new MatchDto(playerOne, playerTwo);
        Match match = new Match(1L, player_1, player_2, player_1);

        when(matchRepositoryMock.save(any(Match.class))).thenReturn(match);

        matchesPersistenceService.save(matchDto);

        verify(matchRepositoryMock, times(1)).save(any(Match.class));
    }

    @Test
    void testFindAll() {
        int pageNumber = 2;
        MatchRepository matchRepositoryMock = mock(MatchRepository.class);
        FinishedMatchesPersistenceService matchesPersistenceService = new FinishedMatchesPersistenceService(matchRepositoryMock);
        List<Match> matches = List.of(new Match(), new Match());

        when(matchRepositoryMock.findAll(anyInt(), anyInt())).thenReturn(matches);

        List<MatchWinnerDto> result = matchesPersistenceService.findAll(pageNumber);

        assertNotNull(result);
        assertEquals(matches.size(), result.size());
        verify(matchRepositoryMock).findAll(anyInt(), eq(pageNumber));

    }

    @Test
    void testFindAllMatchByPlayerName() {
        MatchRepository matchRepositoryMock = mock(MatchRepository.class);
        FinishedMatchesPersistenceService matchesPersistenceService = new FinishedMatchesPersistenceService(matchRepositoryMock);

        String filterByPlayerName = "Volodia";
        int pageNumber = 3;

        List<Match> allByPlayerName = List.of(new Match(), new Match());

        when(matchRepositoryMock.findAllMatchByFilter(anyString(), anyInt(), anyInt()))
                .thenReturn(allByPlayerName);

        List<MatchWinnerDto> result = matchesPersistenceService.findAllMatchByPlayerName(filterByPlayerName, pageNumber);

        assertNotNull(allByPlayerName);
        assertEquals(result.size(), allByPlayerName.size());
        verify(matchRepositoryMock).findAllMatchByFilter(eq(filterByPlayerName), anyInt(), eq(pageNumber));

    }
}