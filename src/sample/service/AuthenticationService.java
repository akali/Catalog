package sample.service;

import sample.model.User;

import java.sql.*;

public class AuthenticationService {
    private static AuthenticationService ourInstance = new AuthenticationService();
    private boolean authenticated;
    private String login;
    private DatabaseService databaseService = DatabaseService.getInstance();

    public static AuthenticationService getInstance() {
        return ourInstance;
    }

    private AuthenticationService() {
        init();
    }

    private void init() {
        this.authenticated = false;
    }

    public boolean authenticate(String login, String password) throws SQLException, ClassNotFoundException {
        if (hasUserWithPassword(login, password)) {
            this.login = login;
            this.authenticated = true;
            return true;
        }
        return false;
    }

    public boolean authenticated() {
        return this.authenticated;
    }

    public String getLogin() {
        return this.login;
    }

    public boolean register(User user) throws SQLException, ClassNotFoundException {
        String login = user.getLogin();
        String password = user.getPassword();
        user.setPassword(password = Hasher.hash(password));

        if (hasUser(login)) {
            return false;
        }

        Connection connection = databaseService.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "insert into user(name, surname, email, country, contact, login, password) values " +
                        "(?, ?, ?, ?, ?, ?, ?)"
        );

        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getCountry());
        statement.setString(5, user.getContact());
        statement.setString(6, user.getLogin());
        statement.setString(7, user.getPassword());

        int count = statement.executeUpdate();
        return count > 0;
    }

    private boolean hasUser(String login) throws SQLException, ClassNotFoundException {
        Connection connection = databaseService.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "select count(*) from user where login=?"
        );
        statement.setString(1, login);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt(1) != 0) {
                return true;
            }
        }

        statement.close();
        connection.close();
        return false;
    }

    private boolean hasUserWithPassword(String login, String password) throws SQLException, ClassNotFoundException {
        password = Hasher.hash(password);
        Connection connection = databaseService.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "select count(*) from user where login=? and password=?"
        );
        statement.setString(1, login);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt(1) != 0) {
                return true;
            }
        }

        statement.close();
        connection.close();
        return false;
    }
}
