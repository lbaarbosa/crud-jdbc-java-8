package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;
import br.com.jdbc.java.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetPeopleListUsingCustomParameters {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the search parameter: ");
        String input = scanner.nextLine();

        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM people WHERE name LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + input + "%");
        ResultSet result = preparedStatement.executeQuery();

        List<Person> people = new ArrayList<>();

        while(result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            people.add(new Person(id,name));
        }

        for(Person p : people) {
            System.out.println(p.getId() + " -> " + p.getName());
        }

        scanner.close();
        preparedStatement.close();
        connection.close();

    }

}
