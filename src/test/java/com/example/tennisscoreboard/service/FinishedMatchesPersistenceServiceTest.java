package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.mapper.MatchMapper;
import com.example.tennisscoreboard.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FinishedMatchesPersistenceServiceTest {

    @Test
    void testSave() {
        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        MatchRepository matchRepositoryMock = mock(MatchRepository.class);
        finishedMatchesPersistenceService.setMatchRepository(matchRepositoryMock);

        MatchDto matchDto = new MatchDto();
        Match match = new Match();

        //when(MatchMapper.INSTANCE.matchDtoToMatch(any())).thenReturn(match);
        when(matchRepositoryMock.save(match)).thenReturn(match);

        finishedMatchesPersistenceService.save(matchDto);

        //   verify(matchRepositoryMock, times(1)).save(match);
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllMatchByPlayerName() {
    }
}