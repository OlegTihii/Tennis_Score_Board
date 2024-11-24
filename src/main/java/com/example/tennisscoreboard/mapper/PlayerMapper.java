package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import com.example.tennisscoreboard.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerMatchDto playerToPlayerMatchDto(Player player);

    Player playerDtoToPlayer(PlayerDto playerDto);
}
