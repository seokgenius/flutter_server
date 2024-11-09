// DBConnection 클래스
package com.example.flutter_server;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {
    private static Connection connection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String username = "system";
            String password = "pass";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}