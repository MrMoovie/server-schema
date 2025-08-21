package com.college.utils;

import com.college.entities.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Component
public class DbUtils {

    private Connection connection;

    @PostConstruct
    public void init () {
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.next();
//        String password = scanner.next();
        createConnection("root", "102030");
    }

    public void createConnection (String username, String password) {
        String url = "jdbc:mysql://localhost:3306/ashcollege";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = java.sql.DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT username, password FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(username, password);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void addUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean auth(String username, String password){
        String query = "SELECT * FROM users WHERE username= ? AND password = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
