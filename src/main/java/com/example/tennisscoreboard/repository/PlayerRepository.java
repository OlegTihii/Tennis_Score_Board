package com.example.tennisscoreboard.repository;

import com.example.tennisscoreboard.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Player, Long> {

    public PlayerRepository() {
        super(Player.class);
    }

    public Optional<Player> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name).uniqueResultOptional();
        }

    }
}
