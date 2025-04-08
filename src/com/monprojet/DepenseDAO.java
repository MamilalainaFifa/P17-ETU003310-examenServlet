package com.monprojet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepenseDAO {
    public void create(Depense depense) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "INSERT INTO depense (libelle, montant, credit_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, depense.getLibelle());
            ps.setDouble(2, depense.getMontant());
            ps.setInt(3, depense.getCreditId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                depense.setId(rs.getInt(1));
            }
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public List<Depense> readAllByCredit(int creditId) throws SQLException {
        Connection con = null;
        List<Depense> depenses = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM depense WHERE credit_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, creditId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depenses.add(new Depense(rs.getInt("id"), rs.getString("libelle"), rs.getDouble("montant"), rs.getInt("credit_id")));
            }
            return depenses;
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
}