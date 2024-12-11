package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.mapper.PlayerMapper;
import com.example.tennisscoreboard.repository.PlayerRepository;

public class PlayerPersistenceService {

    private final PlayerRepository playerRepository = new PlayerRepository();

    public Player findOrSave(PlayerDto playerDto) {
        //todo add logging

        System.out.println("PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto):" + PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto));

        return playerRepository.findByName(playerDto.getName())
                .orElseGet(() -> playerRepository.save(PlayerMapper.INSTANCE.playerDtoToPlayer(playerDto)));
    }
}
