package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Estado;

public class EstadoDAO {

    private Connection conn;

    public EstadoDAO(Connection conn) {
        this.conn = conn;
    }

    // LOCALIZAR ESTADO COM BASE NA UF INSERIDA
    public void localizarEstado(String uf) {
        try {
            // var sql = "select * from estado where uf = '" + uf + "'"; // sucessível a SQL
            // Injection
            var sql = "select * from estado where uf = ?";
            var statement = conn.prepareStatement(sql);
            System.out.println(sql);
            statement.setString(1, uf);
            var result = statement.executeQuery();
            if (result.next()) {
                System.out.printf("Id: %d, Nome: %s, UF: %s\n", result.getInt("id"), result.getString("nome"),
                        result.getString("uf"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erro ao exercutar consulta SQL: " + e.getMessage());
        }
    }

    //CREATE
    public void inserirEstado(Estado estado){
        try{
            String sql = "insert into estado (nome, uf, regiao, areaKm2, populacao) values (?, ?, ?, ?, ?)";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, estado.getNome());
            statement.setString(2, estado.getUf());
            statement.setString(3, estado.getRegiao().getNome());
            statement.setInt(4, estado.getAreaKm2());
            statement.setInt(5, estado.getPopulacao());

        } catch(SQLException e){
            System.out.println("Não foi possível inserir estado no banco. " +e.getMessage());
        }
    }

    /*
    private Long id;
    private String nome;
    private String uf;
    private RegiaoGeografica regiao;
    private int areaKm2;
    private int população;
     * // INSERIR PRODUTOS
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
     */

    //READ

    //UPDATE

    //DELETE

    // LISTAR ESTADOS
    public void listarEstados() {
        try {
            System.out.println("Conexão com o banco realizada com sucesso.");

            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");

            while (result.next()) {
                System.out.printf("Id: %d Nome: %s UF: %s\n", result.getInt("id"), result.getString("nome"),
                        result.getString("uf"));
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
        }
        System.out.println();
    }

}
