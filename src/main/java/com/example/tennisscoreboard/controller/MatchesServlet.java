package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.dto.MatchWinnerDto;
import com.example.tennisscoreboard.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchServlets", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String filterByPlayerName = req.getParameter("filter_by_player_name");

        int pageNumber = (page != null) ? Integer.parseInt(page) : 1;

        //todo не отрабатывает null или когда нет значений
        //todo Не нравится логика if-else
        if (filterByPlayerName != null) {
            List<MatchWinnerDto> findByName = finishedMatchesPersistenceService.findAllMatchByPlayerName(filterByPlayerName, pageNumber);

            req.setAttribute("matches", findByName);
            req.setAttribute("currentPage", pageNumber);


            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } else {
            List<MatchWinnerDto> findAllMatches = finishedMatchesPersistenceService.findAll(pageNumber);

            req.setAttribute("matches", findAllMatches);
            req.setAttribute("currentPage", pageNumber);


            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        }
    }
}
