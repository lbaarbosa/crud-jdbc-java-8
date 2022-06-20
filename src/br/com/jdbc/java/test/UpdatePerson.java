package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;
import br.com.jdbc.java.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdatePerson {
    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner(System.in);
        System.out.println("Type the person's ID: ");
        int inputID = input.nextInt();

        String sql = "SELECT id, name FROM people WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, inputID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            Person person = new Person(resultSet.getInt(1), resultSet.getString(2));
            System.out.println("The current name for the selected person is: " + person.getName() + "\n");
            System.out.println("Type the new desired name: ");
            input.nextLine();
            String newName = input.nextLine();

            sql = "UPDATE people SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, inputID);
            preparedStatement.execute();

            System.out.println("Person successfully updated!");
            System.out.println("The person's new name is: " + newName);

        } else {
            System.out.println("The specified person was not found!");
        }

        connection.close();
        input.close();

    }
}
