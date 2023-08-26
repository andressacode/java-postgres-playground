package com.example.financeiro.model;

import java.util.ArrayList;
import java.util.List;

public class Transacao {
    
    private Integer id;
    private String cliente;
    private double valor;
    private String moeda;
    private String tipo;

    public Transacao(){}

    public Transacao(String cliente, double valor, String moeda, String tipo) {
        this.cliente = cliente;
        this.valor = valor;
        this.moeda = moeda;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Transacao> getLista() {
        return lista;
    }
    
    
    public List<Transacao> lista = new ArrayList<>();

    public void adicionarTransacao(Transacao t) {
        lista.add(t);
    }

    // public List<Transacao> lista filtrar(String cliente){
    //     public List<Transacao> novaLista = new ArrayList<>();
    //     for(Transacao transacao : lista){
    //         if(transacao.getCliente().equals(cliente)){
    //             novaLista.add(transacao);
    //         }
    //     }
    //     return novaLista;
    // }
    
    public double getSaldo(String cliente) {
        double saldo = 0.0;
        for (Transacao t : lista) {
            if (t.cliente.equals(cliente)) {
                saldo += t.valor;
            }
        }
        return saldo;
    }
}
