package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NewPerson {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = scanner.nextLine();

        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO people (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.execute();

        System.out.println("Person successfully inserted!");

        scanner.close();
    }
}
