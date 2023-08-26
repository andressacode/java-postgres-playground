package com.example.estoque.dao;

import java.util.List;
import java.util.Scanner;

import com.example.estoque.entidades.Produto;

public class ProdutoDAO {

    public void listarProdutos(List<Produto> lista) {
        System.out.println("Listando produtos:");
        for (Produto prod : lista) {
            if (lista.isEmpty()) {
                System.out.println("Não existem produtos cadastrados!");
            } else {
                System.out.println(prod);
            }
        }
    }

    public void valorTotalEmEstoque(List<Produto> lista) {
        double valorTotal = 0;
        System.out.print("Valor total em estoque:");
        for (Produto prod : lista) {
            valorTotal = prod.valorTotal();
        }
        System.out.println(valorTotal);
    }

    public int cadastrarProdutos(Scanner sc, List<Produto> lista, int id) {
        Produto produto;
        System.out.println("Cadastrando produtos:");
        System.out.print("Informe o nome do produto: ");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.print("Informe a descricao do produto: ");
        String descricao = sc.nextLine();
        System.out.print("Informe o valor do produto: ");
        double preco = sc.nextDouble();
        System.out.print("Quantos itens existem no estoque? ");
        int quantidade = sc.nextInt();

        produto = new Produto(id, nome, descricao, preco, quantidade);
        id++;
        lista.add(produto);
        System.out.println("Produto cadastrado com sucesso! ");
        System.out.println();
        return id;
    }

    public void buscarProdutoPorId(List<Produto> lista, Scanner sc) {
        System.out.println("Buscar produto por id");
        System.out.println("Informe o id do produto que deseja buscar: ");
        int idBusca = sc.nextInt();
        try {
            for (Produto prod : lista) {
                if (prod.getId() == idBusca) {
                    System.out.print("ID informado: " + prod.getId() + "Nome do produto: " + prod.getNome());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Não existe produto cadastrado para o ID informado. " + e.getMessage());
        }
    }

    public void editarProduto(List<Produto> lista, Scanner sc) {
        System.out.println("Editar produto");
        System.out.println("Informe o id do produto que deseja editar: ");
        int idBusca = sc.nextInt();
        for (Produto prod : lista) {
            if (prod.getId() == idBusca) {
                System.out.print("Novo nome: ");
                prod.setNome(sc.nextLine());
                System.out.print("Nova descrição: ");
                prod.setDescricao(sc.nextLine());
                System.out.print("Novo preço: ");
                prod.setPreco(sc.nextDouble());
                System.out.println("Produto editado com sucesso!");
            } else {
                System.out.println("Não existe produto cadastrado para o ID informado. ");
            }
        }
    }
}
