package com.example.tennisscoreboard.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MatchDto {

    private PlayerMatchDto playerOne;
    private PlayerMatchDto playerTwo;
}
