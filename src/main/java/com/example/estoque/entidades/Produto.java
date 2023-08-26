package com.example.estoque.entidades;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public Produto(){}

    public Produto(Integer id, String nome, String descricao, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(String nome, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public double valorTotal(){
        return quantidade * preco;
    }

    @Override
    public String toString() {
        return "ID: " 
            + id 
            + ", Nome: "
            + nome 
            + ", Descricao: "
            + descricao 
            + ", preco: " 
            + String.format("%.2f", preco)
            + ", Quantidade: " + quantidade;
    }    
}
