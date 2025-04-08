<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Crédit</title>
</head>
<body>
    <h2>Ajouter un Crédit</h2>
    <form action="CreditServlet" method="post">
        Libellé: <input type="text" name="libelle"><br>
        Montant: <input type="number" step="0.01" name="montant"><br>
        <input type="submit" value="Ajouter">
    </form>
    <a href="AdminDashboardServlet">Retour au Dashboard Admin</a>
</body>
</html>