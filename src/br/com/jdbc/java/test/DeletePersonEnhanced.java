package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;
import br.com.jdbc.java.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeletePersonEnhanced {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a person by typing its ID: ");
        int inputID = scanner.nextInt();

        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM people WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, inputID);
        ResultSet result = preparedStatement.executeQuery();

        int affectedRows = 0;
        if (result.next()) {
            List<Person> people = new ArrayList<>();
            int id = result.getInt("id");
            String name = result.getString("name");
            people.add(new Person(id, name));
            for (Person p : people) {
                System.out.println("ID: " + p.getId() + " -> " + p.getName());
            }

            System.out.println("Are you sure about this action?" +
                    "\n Type YES or NO...");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("yes")) {
                sql = "DELETE FROM people WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, inputID);
                affectedRows = preparedStatement.executeUpdate();
            }

        } else {
            System.out.println("The provided ID was not found!");
        }
        if (affectedRows > 0) {
            System.out.println("Changes were made!");
        } else {
            System.out.println("No changes were made!");
        }

        scanner.close();
        connection.close();
    }
}
