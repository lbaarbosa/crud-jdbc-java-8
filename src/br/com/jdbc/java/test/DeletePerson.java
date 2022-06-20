package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletePerson {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the person's ID to be deleted: ");
        int input = scanner.nextInt();

        Connection connection = ConnectionFactory.getConnection();
        String sql = "DELETE FROM people WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, input);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            System.out.println("Changes were made!");
        } else {
            System.out.println("No changes were made!");
        }
        System.out.println("Affected rows: " + affectedRows);

        scanner.close();
        connection.close();

    }
}
