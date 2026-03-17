<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Risultato</title>
</head>
<body>
<h1>Benvenuto!</h1>
<p>Ciao <strong><%= request.getAttribute("nomeUtente") %></strong>!</p>

<br>
<a href="index.html">Torna indietro</a>
</body>
</html>