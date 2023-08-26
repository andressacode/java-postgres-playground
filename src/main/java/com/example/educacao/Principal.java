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
            aluno.setMatricula(1);

            System.out.println("matricula " + aluno.getMatricula());
            double nota = 10.0;
            alunoDao.inserirNota(2, nota, aluno.getMatricula());

            /* CHAMADA PARA LISTAR ALUNOS */
            var listaAlunos = alunoDao.listarAlunos();
            for(Aluno aluno2 : listaAlunos){
                System.out.println(aluno2);
            }

            /* FIM DA CHAMADA PARA LISTAR ALUNOS */

        } catch (SQLException e) {
            System.err.println("Não foi possível conectar ao BD");
        } catch (RuntimeException e) {
            System.err.println("Não foi possível inserir o aluno no BD." +e.getMessage());
        }
    }
}
