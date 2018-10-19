package br.com.caelum.jdbc;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool();

        for (int i = 0; i < 100; i++) {
            Connection connection = connectionPool.getConnection();

            Statement statement = connection.createStatement();
            boolean resultado = statement.execute("select * from Produto");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("nome"));
                System.out.println(resultSet.getString("descricao"));
            }
            resultSet.close();
            statement.close();

            connection.close();
        }
    }


}
