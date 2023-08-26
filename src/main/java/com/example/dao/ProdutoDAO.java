package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Produto;

public class ProdutoDAO {

    private Connection conn;
    Produto produto = new Produto();

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    // EXCLUIR PRODUTOS
    public void excluirProduto(int id) {
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
    public void inserirProduto(Produto produto) {
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

    // LISTAR TODOS OS PRODUTOS DA TABELA
    public void listarProdutos() {
        try {
            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from produto");

            while (result.next()) {
                System.out.printf("Id: %d, Nome: %s", result.getInt("id"), result.getString("nome"));
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível executar consulta SQL. " + e.getMessage());
        }
    }

    // EDITAR PRODUTOS
    public void editarProduto(Produto produto) {
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

    // BUSCAR PRODUTO POR ID
    public void localizarProdutoPorId(int id) {
        var sql = "select * from produto where id = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                System.out.println("Trazendo produto por ID.....");
                System.out.printf("Id: %d, Nome: %s", result.getInt("id"), result.getString("nome") );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por id. " + e.getMessage());
        }
    }
}
