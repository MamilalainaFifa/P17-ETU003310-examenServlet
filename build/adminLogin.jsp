<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion Admin</title>
</head>
<body>
    <h2>Connexion Admin</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    <form action="AdminLoginServlet" method="post">
        Nom: <input type="text" name="nom" placeholder="Admin"><br>
        Mot de passe: <input type="password" name="password" ><br>
        <input type="submit" value="Se connecter">
    </form>
    <a href="DashboardServlet">Retour au Dashboard</a>
</body>
</html>