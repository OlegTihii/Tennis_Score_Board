<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <img src="images/tennis_ball.png" alt="Logo" class="logo">
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div class="nav-links">
            <nav class="nav-links">
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="matches">Matches</a>
            </nav>
            <div class="profile">
                <img src="images/profile.png" alt="Profile Picture">
            </div>
        </div>
    </section>
</header>
<div class="container">
    <div class="score-container">
        <h1 class="score-text">Scorekeeper</h1>
        <img class="score-img" src="images/scorekeeper.png" alt="scorekeeper">
    </div>
    <section class="score">
        <table class="table">
            <thead class="result">
            <tr>
                <th class="table-text">Player</th>
                <th class="table-text">Sets</th>
                <th class="table-text">Games</th>
                <th class="table-text">Points</th>
            </tr>
            </thead>
            <tbody>
            <tr class="player1">
                <td class="table-text">${match.playerOne.playerName}</td>
                <td class="table-text">${match.playerOne.sets}</td>
                <td class="table-text">${match.playerOne.games}</td>
                <td class="table-text">
                    <c:set var="playerOnePoints" value="${match.playerOne.points}"/>
                    <c:set var="playerTwoPoints" value="${match.playerTwo.points}"/>

                    <!-- Проверка на тай-брейк -->
                    <c:choose>
                        <c:when test="${match.playerOne.games == 6 && match.playerTwo.games == 6}">
                            ${match.playerOne.points}
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${playerOnePoints >= 3 && playerTwoPoints >= 3}">
                                    <!-- Оба игрока имеют по 40 очков -->
                                    <c:choose>
                                        <c:when test="${playerOnePoints > playerTwoPoints}">
                                            >
                                        </c:when>
                                        <c:when test="${playerOnePoints < playerTwoPoints}">
                                            <
                                        </c:when>
                                        <c:otherwise>
                                            =
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <!-- Преобразование очков в теннисный формат -->
                                    <c:choose>
                                        <c:when test="${playerOnePoints == 0}">
                                            0
                                        </c:when>
                                        <c:when test="${playerOnePoints == 1}">
                                            15
                                        </c:when>
                                        <c:when test="${playerOnePoints == 2}">
                                            30
                                        </c:when>
                                        <c:when test="${playerOnePoints == 3}">
                                            40
                                        </c:when>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="table-text">
                    <form method="post" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="playerId" value="${match.playerOne.id}"/>
                        <button class="player-btn" type="submit">Point</button>
                    </form>
                </td>
            </tr>
            <tr class="player2">
                <td class="table-text">${match.playerTwo.playerName}</td>
                <td class="table-text">${match.playerTwo.sets}</td>
                <td class="table-text">${match.playerTwo.games}</td>
                <td class="table-text">
                    <c:set var="playerOnePoints" value="${match.playerOne.points}"/>
                    <c:set var="playerTwoPoints" value="${match.playerTwo.points}"/>

                    <c:choose>
                        <c:when test="${match.playerOne.games == 6 && match.playerTwo.games == 6}">
                            ${match.playerTwo.points}
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${playerOnePoints >= 3 && playerTwoPoints >= 3}">
                                    <!-- Оба игрока имеют по 40 очков -->
                                    <c:choose>
                                        <c:when test="${playerOnePoints < playerTwoPoints}">
                                            >
                                        </c:when>
                                        <c:when test="${playerOnePoints > playerTwoPoints}">
                                            <
                                        </c:when>
                                        <c:otherwise>
                                            =
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <!-- Преобразование очков в теннисный формат -->
                                    <c:choose>
                                        <c:when test="${playerTwoPoints == 0}">
                                            0
                                        </c:when>
                                        <c:when test="${playerTwoPoints == 1}">
                                            15
                                        </c:when>
                                        <c:when test="${playerTwoPoints == 2}">
                                            30
                                        </c:when>
                                        <c:when test="${playerTwoPoints == 3}">
                                            40
                                        </c:when>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="table-text">
                    <form method="post" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="playerId" value="${match.playerTwo.id}"/>
                        <button class="player-btn" type="submit">Point</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </section>
<%--        <form method="post" action="match-score?uuid=${uuid}">--%>
<%--        <input type="hidden" name="playerId" value="${match.playerOne.id}"/>--%>
<%--        <button type="submit">${match.playerOne.playerName} wins the point</button>--%>
<%--    </form>--%>
<%--    <form method="post" action="match-score?uuid=${uuid}">--%>
<%--        <input type="hidden" name="playerId" value="${match.playerTwo.id}"/>--%>
<%--        <button type="submit">${match.playerTwo.playerName} wins the point</button>--%>
<%--    </form>--%>
</div>
</body>
</html>
