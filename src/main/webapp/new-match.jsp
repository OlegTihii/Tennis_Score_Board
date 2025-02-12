<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
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
    <div>
        <h1 class="create-title-player">Create New Match</h1>
        <img class="new-match__img" src="images/rocket.png" alt="rocket">
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <div class="center">
            <form method="post" action="new-match">
                <label class="label-player" for="playerOne">Player one</label>
                <input class="input-player" placeholder="Enter the player's name..." type="text" id="playerOne"
                       name="playerOne" pattern="[A-Za-z]\. [A-Za-z]+" required
                       title="Enter a name in the format n. surname ">
                <label class="label-player" for="playerTwo">Player two</label>
                <input class="input-player" placeholder="Enter the player's name..." type="text" id="playerTwo"
                       name="playerTwo" pattern="[A-Za-z]\. [A-Za-z]+" required
                       title="Enter a name in the format n. surname ">
                <input class="btn-player" type="submit" value="Start">
            </form>
        </div>

    </div>
</div>
</body>
</html>
