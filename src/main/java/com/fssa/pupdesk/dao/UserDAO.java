package com.fssa.pupdesk.dao;

import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.User;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    private static final String firstName = "firstname";
    private static final String lastName = "lastname";
    private static final String email = "email";
    private static final String teamCode = "teamcode";
    private static final String password = "password";

    // Connect to database
    public Connection getConnection() throws SQLException {

        String DB_URL;
        String DB_USER;
        String DB_PASSWORD;

        if (System.getenv("CI") != null) {
            DB_URL = System.getenv("DB_URL");
            DB_USER = System.getenv("DB_USER");
            DB_PASSWORD = System.getenv("DB_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            DB_URL = env.get("DB_URL");
            DB_USER = env.get("DB_USER");
            DB_PASSWORD = env.get("DB_PASSWORD");
        }
       return DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "12345678");

    }

    // Get user from DB - Login
    public User login(String email, String password) throws DAOException {
        String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(firstName), rs.getString(lastName), rs.getString(email), rs.getNString(teamCode), rs.getString(password));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Failed to Login");
        }

    }

    public boolean createUser(User user) throws DAOException {
        String insertQuery = "INSERT INTO users (firstname, lastname, email, teamcode, password)VALUES(?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getTeamCode());
            statement.setString(5, user.getPassword());
            // Execute the query
            int rows = statement.executeUpdate();
            // Return successful or not
            return rows == 1;
        } catch (SQLException e) {
            throw new DAOException("Failed to register User");
        }
    }

    //	Updating User from Email
    public User updateUser(String where, String which, String data) throws DAOException {
        String updateQuery = "UPDATE users SET " + which + "= ? WHERE email = ?";
        String selectQuery = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statment = connection.prepareStatement(updateQuery);
         PreparedStatement newStatment = connection.prepareStatement(selectQuery)) {
            statment.setString(1, data);
            statment.setString(2, where);
            int rows = statment.executeUpdate();
            newStatment.setString(1, where);
            ResultSet rs = newStatment.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(firstName), rs.getString(lastName), rs.getString(email), rs.getNString(teamCode), rs.getString(password));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Update Failed");
        }

    }

    //    Deletin the user from the table
    public boolean deleteUser(String email) throws DAOException {
        String deleteQuery = "DELETE FROM users WHERE email =?";
        try (Connection connection = getConnection();
             PreparedStatement statment = connection.prepareStatement(deleteQuery)) {
            statment.setString(1, email);
            int rows = statment.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new DAOException("Failed to Delete");
        }
    }

    public ArrayList<User> getSameTeamUsers(String email, String password) throws DAOException {
        User user = new UserDAO().login(email, password);
        ArrayList<User> members = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE teamcode = ?")) {
            statement.setString(1, user.getTeamCode());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                members.add(new User(rs.getString(firstName), rs.getString(lastName), rs.getString(email), rs.getString(teamCode), rs.getString(password)));
            }
            return members;
        } catch (SQLException e) {
            throw new DAOException("Failed to get team");
        }
    }

}
