package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.mapper.MatchMapper;
import com.example.tennisscoreboard.repository.MatchRepository;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();

    public void save(MatchDto matchDto) {
        Match save = matchRepository.save(MatchMapper.INSTANCE.matchDtoToMatch(matchDto));
        System.out.println("Match: " + save);
    }

}
