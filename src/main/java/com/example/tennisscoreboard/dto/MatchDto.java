package com.example.tennisscoreboard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MatchDto {

    private PlayerMatchDto playerOne;
    private PlayerMatchDto playerTwo;
}
