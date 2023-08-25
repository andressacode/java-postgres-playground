package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AppBd {

    public static void main(String[] args) {
        
        //exceção checada
        try {
            Class.forName("org.postgresql.Driver");
            var conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "gitpod", "");
            System.out.println("Conexão com o banco realizada com sucesso.");
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a biblioteca para o acesso ao banco de dados. " + e.getMessage());
           
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
