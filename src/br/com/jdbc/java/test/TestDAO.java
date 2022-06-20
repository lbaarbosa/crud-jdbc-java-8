package br.com.jdbc.java.test;

import br.com.jdbc.java.infrastructure.DataAccessObject;

public class TestDAO {
    public static void main(String[] args) {

        DataAccessObject dataAccessObject = new DataAccessObject();

        String sql = "INSERT INTO people (name) VALUES (?)";
        dataAccessObject.create(sql, "Maria Rita");
        dataAccessObject.create(sql, "Raul Seixas");
        dataAccessObject.close();

    }
}
