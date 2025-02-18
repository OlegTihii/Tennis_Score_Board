package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.exception.MatchAlreadyInProgressException;
import com.example.tennisscoreboard.mapper.PlayerMapper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID, MatchDto> allGoingMatches = new ConcurrentHashMap<>();
    private final PlayerPersistenceService playerPersistenceService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    public OngoingMatchesService(PlayerPersistenceService playerPersistenceService, FinishedMatchesPersistenceService finishedMatchesPersistenceService) {
        this.playerPersistenceService = playerPersistenceService;
        this.finishedMatchesPersistenceService = finishedMatchesPersistenceService;
    }

    public MatchDto findById(UUID uuid) {
        return allGoingMatches.get(uuid);
    }

    public UUID createMatch(PlayerDto playerOneDto, PlayerDto playerTwoDto) {
        // Проверка есть ли игроки в бд и добавление
        // Проверка играет ли новый добавленный игрок матч. Один и тот же игрок не может играть 2 матча одновременно

        //  playerPersistenceService.setPlayerRepository(new PlayerRepository());

        Player playerOne = playerPersistenceService.findOrSave(playerOneDto);
        Player playerTwo = playerPersistenceService.findOrSave(playerTwoDto);

        System.out.println("Player_1:" + playerOne);
        System.out.println("Player_2:" + playerTwo);

        UUID uuid = UUID.randomUUID();

        MatchDto matchDto = MatchDto.builder()
                .playerOne(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerOne))
                .playerTwo(PlayerMapper.INSTANCE.playerToPlayerMatchDto(playerTwo))
                .build();

        if (allGoingMatches.containsValue(matchDto)) {
            throw new MatchAlreadyInProgressException("The match between the players [" + playerOneDto.getName()
                    + "] and [" + playerTwoDto.getName() + "] has already started");
        }

        allGoingMatches.put(uuid, matchDto);

        return uuid;
    }

    public boolean checkIsMatchOver(UUID uuid, MatchDto matchDto) {
        if (matchDto.getPlayerOne().getSets() == 2 ||
                matchDto.getPlayerTwo().getSets() == 2) {
            finishedMatchesPersistenceService.save(matchDto);
            removeFromGoingMatches(uuid);

            return true;
        }
        return false;
    }

    private void removeFromGoingMatches(UUID uuid) {
        allGoingMatches.remove(uuid);
    }
}
