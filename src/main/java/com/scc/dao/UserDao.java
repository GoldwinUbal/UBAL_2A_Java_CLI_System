package com.scc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scc.model.User;

public class UserDao {

    // REGISTER ACCOUNT
    public boolean registerUser(
            String Username, String Password, String Role) {

        String sql = "INSERT INTO Users " + "(Username, Password, Roles, Status) " + "VALUES (?, ?, ?, 'PENDING')";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, Username);
            pstmt.setString(2, Password);
            pstmt.setString(3, Role.toUpperCase());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Registration successful! Awaiting approval.");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }

        return false;
    }

    // LOGIN
    public User loginUser(String Username, String Password) {

        String sql = "SELECT * FROM Users " + "WHERE Username = ? AND Password = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, Username);
            pstmt.setString(2, Password);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new User(
                            rs.getInt("ID"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("Roles"),
                            rs.getString("Status")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Login failed: " + e.getMessage());
        }

        return null;
    }
}