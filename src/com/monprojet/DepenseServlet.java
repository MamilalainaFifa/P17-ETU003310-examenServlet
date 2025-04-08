package com.monprojet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;
import java.util.List;

public class DepenseServlet extends HttpServlet {
    private CreditDAO creditDAO;
    private DepenseDAO depenseDAO;

    @Override
    public void init() throws ServletException {
        creditDAO = new CreditDAO();
        depenseDAO = new DepenseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLoginServlet");
            return;
        }

        User admin = (User) session.getAttribute("admin");
        try {
            List<Credit> credits = creditDAO.readAllByUser(admin.getId());
            request.setAttribute("credits", credits);
            request.getRequestDispatcher("addDepense.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminLoginServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Vérifier si l'utilisateur est connecté (session admin)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("AdminLoginServlet");
            return;
        }

        // Récupérer les données du formulaire
        String libelle = request.getParameter("libelle");
        double montant = Double.parseDouble(request.getParameter("montant"));
        int creditId = Integer.parseInt(request.getParameter("creditId"));

        try {
            // Récupérer le crédit associé
            CreditDAO creditDAO = new CreditDAO(); // Assurez-vous que cette classe existe
            Credit credit = creditDAO.readById(creditId); // Méthode à ajouter dans CreditDAO
            if (credit == null) {
                request.setAttribute("error", "Crédit non trouvé");
                doGet(request, response); // Réafficher le formulaire
                return;
            }

            // Récupérer les dépenses existantes pour ce crédit
            DepenseDAO depenseDAO = new DepenseDAO(); // Assurez-vous que cette classe existe
            List<Depense> depenses = depenseDAO.readAllByCredit(creditId); // Méthode existante ou à créer
            double totalDepenses = 0;
            for (Depense d : depenses) {
                totalDepenses += d.getMontant();
            }

            // Calculer le reste disponible
            double reste = credit.getMontant() - totalDepenses;

            // Vérifier si le montant de la nouvelle dépense dépasse le reste
            if (montant > reste) {
                request.setAttribute("error", "Le montant de la dépense dépasse le reste disponible (" + reste + " €)");
                doGet(request, response); // Réafficher le formulaire avec le message d’erreur
                return;
            }

            // Si tout est OK, ajouter la dépense
            Depense depense = new Depense(0, libelle, montant, creditId);
            depenseDAO.create(depense);
            response.sendRedirect("AdminDashboardServlet"); // Redirection vers le tableau de bord
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("addDepense.jsp"); // En cas d’erreur SQL
        }
    }

    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // HttpSession session = request.getSession(false);
    // if (session == null || session.getAttribute("admin") == null) {
    // response.sendRedirect("AdminLoginServlet");
    // return;
    // }

    // String libelle = request.getParameter("libelle");
    // double montant = Double.parseDouble(request.getParameter("montant"));
    // int creditId = Integer.parseInt(request.getParameter("creditId"));

    // try {
    // Depense depense = new Depense(0, libelle, montant, creditId);
    // depenseDAO.create(depense);
    // response.sendRedirect("AdminDashboardServlet");
    // } catch (SQLException e) {
    // e.printStackTrace();
    // response.sendRedirect("addDepense.jsp");
    // }
    // }
}