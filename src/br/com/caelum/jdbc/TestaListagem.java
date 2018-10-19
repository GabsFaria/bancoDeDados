package br.com.caelum.jdbc;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");

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
