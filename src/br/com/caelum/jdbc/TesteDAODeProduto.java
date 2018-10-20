package br.com.caelum.jdbc;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TesteDAODeProduto {

    public static void main(String[] args) throws SQLException {
        Produto mesa = new Produto("Mesa vermelha", "Mesao com 4 pés");

        try (Connection connection = new ConnectionPool().getConnection()) {
            ProdutosDAO dao = new ProdutosDAO(connection);
            dao.salva(mesa);
            List<Produto> produtos = dao.lista();
            for (Produto produto :
                    produtos) {
                System.out.println("Existe o produto: "+produto);
            }


        }
    }


}
