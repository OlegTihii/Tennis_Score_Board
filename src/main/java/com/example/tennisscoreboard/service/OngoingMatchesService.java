package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.mapper.PlayerMapper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID, MatchDto> allGoingMatches = new ConcurrentHashMap<>();
    private final PlayerPersistenceService playerPersistenceService = new PlayerPersistenceService();

    public UUID createMatch(PlayerDto playerOneDto, PlayerDto playerTwoDto) {
        // Проверка есть ли игроки в бд и добавление
        Player playerOne = playerPersistenceService.findOrSave(playerOneDto);
        Player playerTwo = playerPersistenceService.findOrSave(playerTwoDto);

        UUID uuid = UUID.randomUUID();

        MatchDto matchDto = MatchDto.builder()
                .playerOne(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerOne))
                .playerTwo(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerTwo))
                .build();

        allGoingMatches.put(uuid, matchDto);

        return uuid;
    }


}
