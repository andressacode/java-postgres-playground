package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AppBd {

    private static final String PASSWORD = "";
    private static final String USERNAME = "gitpod";
    private static final String JDBC_URL = "jdbc:postgresql://localhost/postgres";
    private Connection conn;

    public static void main(String[] args) {
        new AppBd() {
        };
    }

    public AppBd() {
        try (var conn = getConnection()) {
            // carregarDriverJDBC();
            listarEstados(conn);
            //localizarEstado(conn, "PR");
            
            Marca marca = new Marca();
            marca.setId(1L);
            
            Produto produto = new Produto();
            produto.setMarca(marca);
            produto.setNome("Produto nosso teste");
            produto.setValor(120.00);
            inserirProduto(conn, produto);
            listarDadosTabela(conn, "produto");


        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
        }
    }

    // INSERIR PRODUTOS
    private void inserirProduto(Connection conn, Produto produto) {
        try {
            String sql = "insert into produto (nome, marca_id, valor) values(?, ?, ?)";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Não foi possível inserir dados na tabela. ", e.getMessage());
        }
    }

    //LISTAR DADOS DE QUALQUER TABELA
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

            while(result.next()){
                for (int i = 1; i <= cols; i++) {
                    System.out.printf("%-25s | ", result.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao trazer dados da tabela. " +e.getMessage());
        }
    }

    private void localizarEstado(Connection conn, String uf) {
        try {
            // var sql = "select * from estado where uf = '" + uf + "'"; // sucessível a SQL Injection
            var sql = "select * from estado where uf = ?";
            var statement = conn.prepareStatement(sql);
            System.out.println(sql);
            statement.setString(1, uf);
            var result = statement.executeQuery();
            if(result.next()){
                System.out.printf("Id: %d, Nome: %s, UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erro ao exercutar consulta SQL: " + e.getMessage());
        }
    }

    // LISTAR ESTADOS
    private void listarEstados(Connection conn) {
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
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
