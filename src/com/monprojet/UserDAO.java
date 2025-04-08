package com.monprojet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void create(User user) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "INSERT INTO user (nom, password) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public User readByNom(String nom) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM user WHERE nom = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("nom"), rs.getString("password"));
            }
            return null;
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public List<User> readAll() throws SQLException {
        Connection con = null;
        List<User> users = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM user";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("password")));
            }
            return users;
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public void update(User user) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "UPDATE user SET nom = ?, password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }

    public void delete(int id) throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            String query = "DELETE FROM user WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
}