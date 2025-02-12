<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finish match</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
    <section class="match-result">
        <img src="images/frame_matches.png" alt="frame_matches">
        <div class="match-block">
            <h1 class="match-title">Match Result</h1>
            <button class="match-block__btn">
                <a href="matches">
                    View All Matches
                </a>
            </button>
        </div>
    </section>
    <section class="finish-result">
        <h2 class="finish-result__title">Match Result</h2>
        <div class="finish-result__block">
            <div class="winner">
                <span>Winner</span>
                <div class="winner-name">
                    <h3 class="winner-name__text">${match.winnerName}</h3>
                </div>
                <img class="winner-name__img" src="images/cup.png" alt="cup">
            </div>
            <div class="loser">
                <span>Loser</span>
                <div class="loser-name">
                    <c:choose>
                        <c:when test="${match.playerOne==match.winnerName}">
                            <h3 class="loser-name__text">${match.playerTwo}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3 class="loser-name__text">${match.playerOne}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
