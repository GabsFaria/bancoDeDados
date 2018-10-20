package br.com.caelum.jdbc.dao;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    private Connection connection;

    public ProdutosDAO(Connection connection) {

        this.connection = connection;
    }

    public void salva(Produto mesa) throws SQLException {
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

    public List<Produto> lista() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "select  * from Produto";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();

            trasnsformaResultadosEmProdutos(produtos, stmt);

        }
        return produtos;
    }

    public List<Produto> busca(Categoria categoria) throws SQLException {
        System.out.println("Executando uma query");
        List<Produto> produtos = new ArrayList<>();
        String sql = "select  * from Produto where categoria_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, categoria.getId());
            stmt.execute();

            trasnsformaResultadosEmProdutos(produtos, stmt);

        }
        return produtos;
    }

    private void trasnsformaResultadosEmProdutos(List<Produto> produtos, PreparedStatement stmt) throws SQLException {
        try (ResultSet resultSet = stmt.getResultSet()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                Produto produto = new Produto(nome, descricao);
                produto.setId(id);
                produtos.add(produto);
            }

        }
    }
}
