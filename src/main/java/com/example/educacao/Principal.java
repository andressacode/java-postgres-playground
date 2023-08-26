package com.example.educacao;

import java.sql.SQLException;

import com.example.dao.ConnectionManager;
import com.example.educacao.dao.AlunoDAO;
import com.example.educacao.model.Aluno;

public class Principal {

    public static void main(String[] args) {

        try (var conn = ConnectionManager.getConnection()) {
            AlunoDAO alunoDao = new AlunoDAO(conn);
            Aluno aluno = new Aluno();
            aluno.setNome("Rogério");
            alunoDao.inserirAluno(aluno);
            alunoDao.inserirNota(1, 68, 1);
            System.out.println("Aluno inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao BD");
        } catch (RuntimeException e) {
            System.err.println("Não foi possível inserir o aluno no BD." +e.getMessage());
        }
    }
}
