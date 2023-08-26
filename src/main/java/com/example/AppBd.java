package com.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.ConnectionManager;
import com.example.dao.DAO;
import com.example.dao.EstadoDAO;
import com.example.dao.ProdutoDAO;
import com.example.model.Marca;
import com.example.model.Produto;

public abstract class AppBd {

    private Connection conn;

    public static void main(String[] args) {
        new AppBd() {
        };
    }

    public AppBd() {
        try (var conn = ConnectionManager.getConnection()) {

            EstadoDAO estadoDAO = new EstadoDAO(conn);
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            DAO daoGenerico = new DAO(conn);

            estadoDAO.listarEstados();
            estadoDAO.localizarEstado("PR");

            Marca marca = new Marca();
            marca.setId(1L);

            Produto produto = new Produto();
            produto.setMarca(marca);
            produto.setNome("Produto novo");
            produto.setValor(95.00);
            // inserirProduto(conn, produto);
            produto.setId(200L);
            produtoDAO.editarProduto(produto);
            // excluirProduto(conn, 200);
            daoGenerico.listarDadosTabela("produto");

        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
        }
    }

    private void carregarDriverJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(
                    "Não foi possível carregar a biblioteca para o acesso ao banco de dados. " + e.getMessage());

        }
    }

}
