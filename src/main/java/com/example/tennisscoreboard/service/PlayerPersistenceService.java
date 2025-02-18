package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.mapper.PlayerMapper;
import com.example.tennisscoreboard.repository.PlayerRepository;

public class PlayerPersistenceService {

    private final PlayerRepository playerRepository;

    public PlayerPersistenceService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findOrSave(PlayerDto playerDto) {

        System.out.println("PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto):" + PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto));

        return playerRepository.findByName(playerDto.getName())
                .orElseGet(() -> playerRepository.save(PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto)));
    }
}
