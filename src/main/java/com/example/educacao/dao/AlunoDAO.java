package com.example.educacao.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.DAO;
import com.example.educacao.model.Aluno;

public class AlunoDAO extends DAO {

    public AlunoDAO(Connection conn) {
        super(conn);
    }

    // INSERIR ALUNO (CREATE)
    public void inserirAluno(Aluno aluno) {
        try {
            var sql = "insert into aluno (nome) values (?)";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // INSERIR NOTA DO ALUNO
    public void inserirNota(int idDisciplina, double nota, int matricula) {
        if (idDisciplina <= 0 || idDisciplina > 3) {
            throw new IllegalArgumentException("O id da disciplina deve estar entre 1 e 3");
        }
        try {
            var sql = "update aluno set nota" + idDisciplina + " = ? where matricula = ?";
            System.out.println(sql);
            var statement = conn.prepareStatement(sql);
            statement.setDouble(1, nota);
            statement.setInt(2, matricula);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
