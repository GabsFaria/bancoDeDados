package br.com.caelum.jdbc;

import br.com.caelum.jdbc.modelo.Produto;

import java.sql.*;

public class TesteInsercaoDeProduto {

    public static void main(String[] args) throws SQLException {
        Produto mesa = new Produto("Mesa azul", "Mesao com 4 pés");

        try (Connection connection = new ConnectionPool().getConnection()) {
            String sql = "insert into Produto(nome, descricao) values(?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, mesa.getNome());
                preparedStatement.setString(2 ,mesa.getDescricao());
                preparedStatement.execute();

                try (ResultSet rs = preparedStatement.getGeneratedKeys()){
                    if (rs.next()) {
                        mesa.setId(rs.getInt("id"));
                    }

                }


            }

        }
    }
}
