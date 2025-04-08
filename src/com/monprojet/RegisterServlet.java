package com.monprojet;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");

        try {
            if (userDAO.readByNom(nom) != null) {
                request.setAttribute("error", "Ce nom est déjà pris");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                User user = new User(0, nom, password);
                userDAO.create(user);
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp");
        }
    }
}