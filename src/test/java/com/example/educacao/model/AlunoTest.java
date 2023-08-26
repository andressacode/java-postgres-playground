package com.example.educacao.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlunoTest {

    @Test
    void testCalculaMedia() {
        Aluno aluno = new Aluno();

        aluno.setNota1(10.0);
        aluno.setNota2(8.0);
        aluno.setNota3(8.0);
        double esperado = 8.66;
        double obtido = aluno.calculaMedia();
        assertEquals(esperado, obtido, 0.01);

    }

    @Test
    void calculaMediaValoresIguais() {
        Aluno aluno = new Aluno();

        aluno.setNota1(10.0);
        aluno.setNota2(10.0);
        aluno.setNota3(10.0);
        double esperado = 10;
        double obtido = aluno.calculaMedia();
        assertEquals(esperado, obtido, 0.01);

    }
}
