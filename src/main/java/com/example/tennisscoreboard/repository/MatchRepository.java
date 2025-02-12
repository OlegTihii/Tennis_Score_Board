package com.example.tennisscoreboard.repository;

import com.example.tennisscoreboard.entity.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MatchRepository extends BaseRepository<Match, Long> {

    public MatchRepository() {
        super(Match.class);
    }


    public List<Match> findAll(int pageSize, int pageNumber) {
        try (Session session = sessionFactory.openSession()) {
                Query<Match> findAllMatch = session.createQuery("SELECT m FROM Match m", Match.class);
                findAllMatch.setFirstResult((pageNumber - 1) * pageSize);
                findAllMatch.setMaxResults(pageSize);

                return findAllMatch.list();
        }
    }

    public List<Match> findAllMatchByFilter(String filterByPlayerName, int pageSize, int pageNumber) {
        try (Session session = sessionFactory.openSession()) {
            Query<Match> MatchByFilter = session.createQuery("SELECT m FROM Match m " +
                            "WHERE m.playerOne.name = :playerName OR m.playerTwo.name = :playerName", Match.class)
                    .setParameter("playerName", filterByPlayerName);
            MatchByFilter.setFirstResult((pageNumber - 1) * pageSize);
            MatchByFilter.setMaxResults(pageSize);

            return MatchByFilter.list();
        }
    }
}
