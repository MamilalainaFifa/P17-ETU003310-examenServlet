<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <h2>Connexion</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    <form action="LoginServlet" method="post">
        Nom: <input type="text" name="nom"><br>
        Mot de passe: <input type="password" name="password"><br>
        <input type="submit" value="Se connecter">
    </form>
    <a href="RegisterServlet">Pas de compte ? Inscrivez-vous</a>
</body>
</html>