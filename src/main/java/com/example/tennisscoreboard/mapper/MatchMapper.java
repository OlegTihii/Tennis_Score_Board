package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchWinnerDto matchDtoToMatchWinnerDto(MatchDto matchDto);

    Match matchDtoToMatch(MatchDto matchDto);

    @Named("choiceWinner")
    default Player choiceWinner(MatchDto matchDto) {
        if (matchDto.getPlayerOne().getSets() > matchDto.getPlayerTwo().getSets()) {
            return new Player(
                    matchDto.getPlayerOne().getId(),
                    matchDto.getPlayerOne().getName()
            );
        } else {
            return new Player(
                    matchDto.getPlayerTwo().getId(),
                    matchDto.getPlayerTwo().getName()
            );
        }
    }
}
