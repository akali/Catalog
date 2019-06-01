package sample.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private static DatabaseService ourInstance = new DatabaseService();

    public static DatabaseService getInstance() {
        return ourInstance;
    }

    private DatabaseService() {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demodb?useSSL=false",
                "demouser",
                "demopassword");
    }
}
