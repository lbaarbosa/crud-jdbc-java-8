package br.com.jdbc.java.test;

import br.com.jdbc.java.models.ConnectionFactory;
import br.com.jdbc.java.models.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetPeopleList {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM people";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<Person> people = new ArrayList<>();

        while(result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            people.add(new Person(id,name));
        }

        for(Person p : people) {
            System.out.println(p.getId() + " -> " + p.getName());
        }

        statement.close();
        connection.close();

    }
}
