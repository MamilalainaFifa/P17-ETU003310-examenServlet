package com.monprojet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO {
    public void create(Credit credit) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "INSERT INTO credit (libelle, montant, user_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, credit.getLibelle());
            ps.setDouble(2, credit.getMontant());
            ps.setInt(3, credit.getUserId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                credit.setId(rs.getInt(1));
            }
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public List<Credit> readAll() throws SQLException {
        Connection con = null;
        List<Credit> credits = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM credit";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                credits.add(new Credit(rs.getInt("id"), rs.getString("libelle"), rs.getDouble("montant"),
                        rs.getInt("user_id")));
            }
            return credits;
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public List<Credit> readAllByUser(int userId) throws SQLException {
        Connection con = null;
        List<Credit> credits = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM credit WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                credits.add(new Credit(rs.getInt("id"), rs.getString("libelle"), rs.getDouble("montant"),
                        rs.getInt("user_id")));
            }
            return credits;
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public Credit readById(int id) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection(); // Méthode pour obtenir une connexion à la base
            String query = "SELECT * FROM credit WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Credit(rs.getInt("id"), rs.getString("libelle"),
                        rs.getDouble("montant"), rs.getInt("user_id"));
            }
            return null;
        } finally {
            DatabaseConnection.closeConnection(con); // Fermer la connexion
        }
    }
}