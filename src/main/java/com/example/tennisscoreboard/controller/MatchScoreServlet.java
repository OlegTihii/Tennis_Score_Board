package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.dto.MatchDto;
import com.example.tennisscoreboard.mapper.MatchMapper;
import com.example.tennisscoreboard.service.MatchScoreCalculationService;
import com.example.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchDto matchDto = ongoingMatchesService.findById(uuid);

        req.setAttribute("match", matchDto);
        req.setAttribute("uuid", uuid);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Long id = Long.valueOf(req.getParameter("playerId"));

        MatchDto matchDto = ongoingMatchesService.findById(uuid);
//        matchDto.getPlayerOne().setSets(1); // временная
//        matchDto.getPlayerOne().setGames(2); // временная
//        matchDto.getPlayerOne().setPoints(3); // временная
        matchScoreCalculationService.updateScore(matchDto, id);

        if (ongoingMatchesService.checkIsMatchOver(uuid, matchDto)) {
            req.setAttribute("match", MatchMapper.INSTANCE.matchDtoToMatchWinnerDto(matchDto)); //Зачем мне из дто делать дто
            req.getRequestDispatcher("/finish-match.jsp").forward(req, resp);
        }

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
