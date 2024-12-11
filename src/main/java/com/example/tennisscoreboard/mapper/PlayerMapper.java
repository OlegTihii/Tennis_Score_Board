package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import com.example.tennisscoreboard.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "sets", ignore = true)
    @Mapping(target = "points", ignore = true)
    @Mapping(target = "games", ignore = true)
    PlayerMatchDto playerToPlayerMatchDto(Player player);
    Player playerDtoToPlayer(PlayerDto playerDto);
}
