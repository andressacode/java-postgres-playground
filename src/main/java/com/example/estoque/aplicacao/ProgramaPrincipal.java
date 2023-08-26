package com.example.estoque.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.example.estoque.dao.ProdutoDAO;
import com.example.estoque.entidades.Produto;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> lista = new ArrayList<>();

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int id = 0;

        String menu = """
                --------| MENU |-------
                1 - Cadastrar produto
                2 - Listar produtos
                3 - Buscar produto por id
                4 - Editar produto
                5 - Remover produto
                6 - Valor total em estoque
                7 - Sair do programa
                Informe sua opção:
                """;

        int opcao = 0;
        while (opcao != 7) {
            System.out.print(menu);
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    produtoDAO.cadastrarProdutos(sc, lista, id);
                    break;

                case 2:
                    produtoDAO.listarProdutos(lista);
                    break;

                case 3:
                    produtoDAO.buscarProdutoPorId(lista, sc);
                    break;

                case 4:
                    produtoDAO.editarProduto(lista, sc);
                    break;

                case 6:
                    produtoDAO.valorTotalEmEstoque(lista);
                    break;

                case 7:
                    System.out.println("Encerrando aplicacao......");
                    System.out.println("Aplicação encerrada!");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        sc.close();
    }

}