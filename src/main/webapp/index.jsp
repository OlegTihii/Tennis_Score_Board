<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home page</title>
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
<main>
    <section class="welcome-section">
        <div class="container">
            <h1>Welcome to Tennis Scoreboard</h1>
            <p>Manage your tennis matches, record results, and track rankings</p>
            <img src="images/welcome_image.png" alt="Welcome Image" class="welcome-image">
            <div class="button-container">
                <a href="new-match">
                    <button class="btn start-match">
                        Start a new match
                    </button>
                </a>
                <a href="matches">
                    <button class="btn view-results">
                        View match results
                    </button>
                </a>
            </div>
        </div>
    </section>
</main>
</body>
</html>