package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.dto.PlayerMatchDto;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);


    @Mapping(target = "playerOne", source = "playerOne.playerName")
    @Mapping(target = "playerTwo", source = "playerTwo.playerName")
    @Mapping(target = "winnerName", source = ".", qualifiedByName = "choiceWinnerForMatchWinnerDto")
    MatchWinnerDto matchDtoToMatchWinnerDto(MatchDto matchDto);


    @Mapping(target = "playerOne", source = "playerOne")
    @Mapping(target = "playerTwo", source = "playerTwo")
    @Mapping(target = "winner", source = ".", qualifiedByName = "choiceWinnerForMatch")
    Match matchDtoToMatch(MatchDto matchDto);

    @Mapping(target = "playerOne", source = "playerOne.name")
    @Mapping(target = "playerTwo", source = "playerTwo.name")
    @Mapping(target = "winnerName",  source = "winner.name")
    MatchWinnerDto matchToMatchWinnerDto (Match match);

    @Named("choiceWinnerForMatchWinnerDto")
    default String choiceWinnerForMatchWinnerDto(MatchDto matchDto) {
        PlayerMatchDto playerOne = matchDto.getPlayerOne();
        PlayerMatchDto playerTwo = matchDto.getPlayerTwo();
        if (playerOne.getSets() > playerTwo.getSets()) {
            return playerOne.getPlayerName();
        } else {
            return playerTwo.getPlayerName();
        }
    }

    @Named("choiceWinnerForMatch")
    default Player choiceWinnerForMatch(MatchDto matchDto) {
        Player winnerPlayer = new Player();

        PlayerMatchDto playerOne = matchDto.getPlayerOne();
        PlayerMatchDto playerTwo = matchDto.getPlayerTwo();

        if (playerOne.getSets() > playerTwo.getSets()) {
           winnerPlayer.setName(playerOne.getPlayerName());
           winnerPlayer.setId(playerOne.getId());
        } else {
            winnerPlayer.setName(playerTwo.getPlayerName());
            winnerPlayer.setId(playerTwo.getId());
        }
        return winnerPlayer;
    }
}
