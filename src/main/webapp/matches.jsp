<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Matches</title>
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
    <h1>Matches</h1>
<%--    <form method="get" action="matches">--%>
        <div class="input-container">

                <form class="form-matches" method="get" action="matches">
                    <input class="input-filter" placeholder="Find a match..." type="text" id="filter_by_player_name"
                           name="filter_by_player_name"
                           value="${param.filter_by_player_name}" pattern=".*\S.*" required
                           title="Player name cannot be empty or just spaces.">
                </form>

            <div>
                <a href="/matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>
<%--    </form>--%>

    <table class="table-matches">
        <tr>
            <th>Player One</th>
            <th>Player Two</th>
            <th>Winner</th>
        </tr>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.playerOne}</td>
                <td>${match.playerTwo}</td>
                <td><span class="winner-name-td">${match.winnerName}</span></td>
            </tr>
        </c:forEach>
    </table>

    <%-- Пагинация --%>
    <div class="pagination">
        <c:if test="${totalPages > 1}">
            <c:if test="${currentPage > 1}">
                <c:choose>
                    <c:when test="${not empty param.filter_by_player_name}">
                        <a class="prev"
                           href="matches?page=${currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}">
                            < </a>
                    </c:when>
                    <c:otherwise>
                        <a class="prev" href="matches?page=${currentPage - 1}"> < </a>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <strong>${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${empty param.filter_by_player_name}">
                                <a class="num-page" href="matches?page=${i}">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="num-page"
                                   href="matches?page=${i}&filter_by_player_name=${param.filter_by_player_name}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <c:choose>
                    <c:when test="${not empty param.filter_by_player_name}">
                        <a class="next"
                           href="matches?page=${currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}">
                            > </a>
                    </c:when>
                    <c:otherwise>
                        <a class="next" href="matches?page=${currentPage + 1}"> > </a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:if>
    </div>
</div>


</body>

</html>
