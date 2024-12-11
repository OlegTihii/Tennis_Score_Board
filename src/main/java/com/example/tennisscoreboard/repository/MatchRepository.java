package com.example.tennisscoreboard.repository;

import com.example.tennisscoreboard.entity.Match;

public class MatchRepository extends BaseRepository<Match, Long> {

    public MatchRepository() {
        super(Match.class);
    }



}
