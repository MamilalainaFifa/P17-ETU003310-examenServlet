<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.monprojet.User" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Accueil</title>
        </head>
 
        <body>
            <h2>Bienvenue</h2>
            <% User user=(User) session.getAttribute("user"); if (user !=null) { %>
                <p>ID: <%= user.getId() %>
                </p>
                <p>Nom: <%= user.getNom() %>
                </p>
                <p>Password: <%= user.getPassword() %>
                </p>
                <% } else { response.sendRedirect("login.jsp"); } %>
                    <a href="login.jsp">Déconnexion</a>
        </body>

        </html>