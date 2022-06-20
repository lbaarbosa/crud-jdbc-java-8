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

public class UpdatePersonUsingNameSearch {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the person to be updated: ");
        String chosenPerson = scanner.nextLine();

        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM people WHERE name LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + chosenPerson + "%");
        ResultSet result = preparedStatement.executeQuery();

        if(result.next()) {

            List<Person> people = new ArrayList<>();

            int id = result.getInt("id");
            String name = result.getString("name");
            people.add(new Person(id,name));

            int personID = 0;
            String oldName = null;

            for(Person p : people) {
                personID = p.getId();
                oldName = p.getName();
                System.out.println(p.getId() + " -> " + p.getName());
            }

            System.out.println("Type the new desired name: ");
            String selectedName = scanner.nextLine();

            sql = "UPDATE people SET name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, selectedName);
            preparedStatement.setInt(2, personID);
            preparedStatement.execute();

            System.out.println("Changes were made!");
            System.out.println("The old name was: " + oldName);
            System.out.println("The new name is: " + selectedName + "\nFinished!");
        } else {
            System.out.println("The specified person was not found!");
        }

        scanner.close();
        preparedStatement.close();
        connection.close();

    }



}
