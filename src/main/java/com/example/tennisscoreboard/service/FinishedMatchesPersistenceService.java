package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.mapper.MatchMapper;
import com.example.tennisscoreboard.repository.MatchRepository;

import java.util.List;

public class FinishedMatchesPersistenceService {

    //todo pageSize нарушает принцип единство ответственности
    private final int pageSize = 5;
    private MatchRepository matchRepository;



    public void save(MatchDto matchDto) {
        Match save = matchRepository.save(MatchMapper.INSTANCE.matchDtoToMatch(matchDto));
        System.out.println("Match: " + save);
    }

    public List<MatchWinnerDto> findAll(int pageNumber) {
        List<Match> all = matchRepository.findAll(pageSize, pageNumber);
        return all.stream()
                .map(MatchMapper.INSTANCE::matchToMatchWinnerDto)
                .toList();
    }


    public List<MatchWinnerDto> findAllMatchByPlayerName(String filterByPlayerName, int pageNumber) {
        List<Match> allByPlayerName = matchRepository.findAllMatchByFilter(filterByPlayerName, pageSize, pageNumber);
        return allByPlayerName.stream()
                .map(MatchMapper.INSTANCE::matchToMatchWinnerDto)
                .toList();
    }

    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
}
