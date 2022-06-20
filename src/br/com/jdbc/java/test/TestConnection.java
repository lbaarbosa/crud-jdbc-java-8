package br.com.jdbc.java.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306?verifyServerCertificate=false&useSSL=true";
        final String user = "root";
        final String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("The connection was successfully established!");
        connection.close();

    }
}
