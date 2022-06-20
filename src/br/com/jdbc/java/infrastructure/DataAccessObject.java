package br.com.jdbc.java.infrastructure;

import br.com.jdbc.java.models.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessObject {

    private Connection connection;

    public int create(String sql, Object... atributes) {
        try {
            PreparedStatement preparedStatement =
                    getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            addAtributes(preparedStatement, atributes);

            if(preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            return -1;
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close() {
        try {
            getConnection().close();
        } catch (SQLException ex) {}
        finally {
            connection = null;
        }
    }

    private void addAtributes(PreparedStatement preparedStatement, Object[] atributes) throws SQLException {
        int index = 1;
        for (Object atribute : atributes) {
            if(atribute instanceof String) {
                preparedStatement.setString(index, (String) atribute);
            } else if(atribute instanceof Integer) {
                preparedStatement.setInt(index, (Integer) atribute);
            }
            index++;
        }
    }

    private Connection getConnection() {
        try {
            if(connection != null && !connection.isClosed()) {
                return connection;
            }
        } catch (SQLException ex){}
        connection = ConnectionFactory.getConnection();
        return connection;
    }
}
