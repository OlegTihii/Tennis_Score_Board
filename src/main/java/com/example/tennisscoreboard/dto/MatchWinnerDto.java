package com.example.tennisscoreboard.dto;

import lombok.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MatchWinnerDto {

    private String playerOne;
    private String playerTwo;
    private String winnerName;

}
