package com.example.estoque.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProdutoTest {

    @Test
    void deveRetornarOValorTotalEmEstoque() {
        
        Produto produto = new Produto();

        // tenho que passar o valor do produto
        // tenho que passar o valor que eu espero obter
        //tenho que passar a quantidade
        produto.setPreco(340.00);
        produto.setQuantidade(2);
        double esperado = 680.00;
        double obtido = produto.valorTotal();

        assertEquals(esperado, obtido);
    }
}
