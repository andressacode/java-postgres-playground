package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Produto;

public class ProdutoDAO {

    private Connection conn;

    Produto produto = new Produto();

    public ProdutoDAO(Connection conn){
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


    
}
