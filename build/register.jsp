<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
    <h2>Inscription</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    <form action="RegisterServlet" method="post">
        Nom: <input type="text" name="nom"><br>
        Mot de passe: <input type="password" name="password"><br>
        <input type="submit" value="S'inscrire">
    </form>
    <a href="LoginServlet">Déjà inscrit ? Connectez-vous</a>
</body>
</html>