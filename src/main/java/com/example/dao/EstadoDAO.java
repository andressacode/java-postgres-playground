package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class EstadoDAO {

    private Connection conn;

    public EstadoDAO(Connection conn) {
        this.conn = conn;
    }

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
