package com.example.tennisscoreboard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MatchWinnerDto {

    private String playerOne;
    private String playerTwo;
    private String winner;

}
