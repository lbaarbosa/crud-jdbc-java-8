package br.com.jdbc.java.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) throws SQLException {

        final String url = "jdbc:mysql://localhost:3306?verifyServerCertificate=false&useSSL=true";
        final String user = "root";
        final String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS curso_java");
        System.out.println("Database successfully created!");

        connection.close();
    }

}
