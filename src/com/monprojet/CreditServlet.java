package com.monprojet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;

public class CreditServlet extends HttpServlet {
    private CreditDAO creditDAO;

    @Override
    public void init() throws ServletException {
        creditDAO = new CreditDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLoginServlet");
            return;
        }
        request.getRequestDispatcher("addCredit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLoginServlet");
            return;
        }

        String libelle = request.getParameter("libelle");
        double montant = Double.parseDouble(request.getParameter("montant"));
        User admin = (User) session.getAttribute("admin");

        try {
            Credit credit = new Credit(0, libelle, montant, admin.getId());
            creditDAO.create(credit);
            response.sendRedirect("AdminDashboardServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("addCredit.jsp");
        }
    }
}