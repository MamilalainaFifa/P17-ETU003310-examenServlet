package com.monprojet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    private CreditDAO creditDAO;
    private DepenseDAO depenseDAO;

    @Override
public void init() throws ServletException {
    creditDAO = new CreditDAO();
    depenseDAO = new DepenseDAO();
    System.out.println("DashboardServlet initialisé");
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Credit> credits = creditDAO.readAll();
            for (Credit credit : credits) {
                List<Depense> depenses = depenseDAO.readAllByCredit(credit.getId());
                credit.setDepenses(depenses);
            }
            request.setAttribute("credits", credits);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}