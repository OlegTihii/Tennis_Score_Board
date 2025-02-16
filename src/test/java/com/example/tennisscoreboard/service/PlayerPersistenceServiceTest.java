package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.entity.Player;
import com.example.tennisscoreboard.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class PlayerPersistenceServiceTest {

    private void setPrivateField(Object target, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField("playerRepository");
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void testFindOrSave_PlayerExist() throws Exception {
        PlayerPersistenceService playerPersistenceService = new PlayerPersistenceService();
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        setPrivateField(playerPersistenceService, playerRepository);

        PlayerDto newPlayerForDb = new PlayerDto("Igor");
        Player existingPlayer = new Player(1L, "Igor");

        when(playerRepository.findByName("Igor")).thenReturn(Optional.of(existingPlayer));

        Player result = playerPersistenceService.findOrSave(newPlayerForDb);

        assertEquals(existingPlayer, result);
    }


}