package br.com.caelum.jdbc;

import java.sql.*;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {

        try (Connection connection =  new ConnectionPool().getConnection()) {

            connection.setAutoCommit(false);
            String sql = "insert into Produto (nome, descricao) values(?, ?) ";

            try(PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);) {

                adiciona("TV LCD", "32 polegadas", statement);
                adiciona("Blueray", "Full HDMI", statement);
                connection.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                connection.rollback();
                System.out.println("Rollback efetuado");
            }

        }

    }

    private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {

        if (nome.equals("Blueray")){
            throw new IllegalArgumentException("Problema ocorrido");
        }

        statement.setString(1, nome);
        statement.setString(2, descricao);

        boolean resultado = statement.execute();
        System.out.println(resultado);

        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()){
            System.out.println(resultSet.getString("id"));
//            System.out.println(resultSet.getString("nome"));
//            System.out.println(resultSet.getString("descricao"));
        }

        resultSet.close();
    }
}
