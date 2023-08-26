package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.dao.ConnectionManager;
import com.example.dao.EstadoDAO;
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
            editarProduto(conn, produto);
            // excluirProduto(conn, 200);
            listarDadosTabela(conn, "produto");

        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
        }
    }

    // EXCLUIR PRODUTOS
    private void excluirProduto(Connection conn, int id) {
        var sql = "delete from produto where id = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
                System.out.println("Produto excluído com sucesso. ");
            } else {
                System.out.println("O produto não foi localizado. ");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dado da tabela. " + e.getMessage());
        }
    }

    // INSERIR PRODUTOS
    private void inserirProduto(Connection conn, Produto produto) {
        String sql = "insert into produto (nome, marca_id, valor) values(?, ?, ?)";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Não foi possível inserir dados na tabela. ", e.getMessage());
        }
    }

    // LISTAR DADOS DE QUALQUER TABELA
    private void listarDadosTabela(Connection conn, String tabela) {

        var sql = "select * from " + tabela;
        // System.out.println(sql);
        try {
            var statement = conn.createStatement();
            var result = statement.executeQuery(sql);

            var metadata = result.getMetaData();
            int cols = metadata.getColumnCount();

            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-25s | ", metadata.getColumnName(i));
            }
            System.out.println();

            while (result.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.printf("%-25s | ", result.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao trazer dados da tabela. " + e.getMessage());
        }
    }

    // EDITAR PRODUTOS
    private void editarProduto(Connection conn, Produto produto) {
        String sql = "update produto set nome = ? , marca_id = ?, valor = ? where id = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.setLong(4, produto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.printf("Não foi possível alterar os dados na tabela. ", e.getMessage());
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
