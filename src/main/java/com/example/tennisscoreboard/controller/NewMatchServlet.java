package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.dto.PlayerDto;
import com.example.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String playerOneParameter = req.getParameter("playerOne");
        String playerTwoParameter = req.getParameter("playerTwo");

        //todo проверка на идентичность имен

        PlayerDto playerOne = PlayerDto.builder()
                .name(playerOneParameter)
                .build();

        PlayerDto playerTwo = PlayerDto.builder()
                .name(playerTwoParameter)
                .build();

        UUID uuid = ongoingMatchesService.createMatch(playerOne, playerTwo);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
