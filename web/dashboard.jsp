<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.monprojet.Credit" %>
<%@ page import="com.monprojet.Depense" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
        
    <a href="AdminLoginServlet">Admin</a>
    <h2>Liste des Crédits et Dépenses</h2>
    <% List<Credit> credits = (List<Credit>) request.getAttribute("credits"); %>
    <% if (credits != null) { %>
        <% for (Credit credit : credits) { %>
            <h3><%= credit.getLibelle() %> - Montant: <%= credit.getMontant() %></h3>
            <table border="1">
                <tr>
                    <th>Libellé</th>
                    <th>Somme</th>
                    <th>Reste</th>
                </tr>
                <% 
                    List<Depense> depenses = credit.getDepenses();
                    double totalDepenses = 0;
                    if (depenses != null) {
                        for (Depense depense : depenses) {
                            totalDepenses += depense.getMontant();
                %>
                    <tr>
                        <td><%= depense.getLibelle() %></td>
                        <td><%= depense.getMontant() %></td>
                        <td><%= credit.getMontant() - totalDepenses %></td>
                    </tr>
                <% } } %>
            </table>
            <p>Reste total: <%= credit.getMontant() - totalDepenses %></p>
            <br>
            <br>
        <% } %>
    <% } %>

</body>
</html>