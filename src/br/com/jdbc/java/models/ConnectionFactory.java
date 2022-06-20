package br.com.jdbc.java.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection() {

        try {
            Properties properties = getProperties();
            final String url = properties.getProperty("database.url");
            final String user = properties.getProperty("database.user");
            final String password = properties.getProperty("database.password");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        String path = "./connection.properties";
        properties.load(ConnectionFactory.class.getResourceAsStream(path));
        return properties;
    }

}