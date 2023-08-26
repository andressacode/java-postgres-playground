package com.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.ConnectionManager;
import com.example.dao.DAO;
import com.example.dao.EstadoDAO;
import com.example.dao.ProdutoDAO;
import com.example.model.Estado;
import com.example.model.Marca;
import com.example.model.Produto;
import com.example.model.RegiaoGeografica;

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

            // estadoDAO.listarEstados();
            // estadoDAO.localizarEstado("PR");

            Marca marca = new Marca();
            marca.setId(1L);

            RegiaoGeografica regiao = new RegiaoGeografica();
            regiao.setNome("Região de teste");

            Produto produto = new Produto();
            produto.setMarca(marca);
            produto.setNome("Produto novo");
            produto.setValor(95.00);
            produto.setId(200L);
            
            Estado estado = new Estado();
            estado.setNome("Uirapuru");
            estado.setUf("Seresteiro");
            estado.setAreaKm2(250);
            estado.setPopulacao(450);
            estadoDAO.inserirEstado(estado);

            //produtoDAO.localizarProdutoPorId(190);

        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao banco de dados." + e.getMessage());
        }
    }

}
