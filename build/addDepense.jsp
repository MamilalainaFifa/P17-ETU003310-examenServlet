<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.monprojet.Credit" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une Dépense</title>
</head>
<body>
    <h2>Ajouter une Dépense</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    <form action="DepenseServlet" method="post">
        Libellé : <input type="text" name="libelle"><br>
        Montant : <input type="number" step="0.01" name="montant"><br>
        Crédit : 
        <select name="creditId">
            <% List<Credit> credits = (List<Credit>) request.getAttribute("credits"); %>
            <% if (credits != null) { %>
                <% for (Credit credit : credits) { %>
                    <option value="<%= credit.getId() %>">
                        <%= credit.getLibelle() %> (<%= credit.getMontant() %>)
                    </option>
                <% } %>
            <% } %>
        </select><br>
        <input type="submit" value="Ajouter">
    </form>
    <a href="AdminDashboardServlet">Retour au Dashboard Admin</a>
</body>
</html>