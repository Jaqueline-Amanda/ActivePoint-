package br.com.curso.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Turma {
   
    private int idTurma;
    private String numTurma;
    private List<Aluno> alunos;
    private String situacao;
    private Disciplina disciplina;

    public Turma(int idTurma, String numTurma,  List<Aluno> alunos, String situacao, Disciplina disciplina) {
        this.idTurma = idTurma;
        this.numTurma = numTurma;
        this.situacao = situacao;
        this.disciplina = disciplina;
        this.alunos = alunos;
       
    }

    public Turma() throws ParseException{
        this.idTurma = 0;
        this.numTurma = "";
        this.alunos = new ArrayList<>();
        this.situacao = "A";
        this.disciplina = new Disciplina();
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNumTurma() {
        return numTurma;
    }

    public void setNumTurma(String numTurma) {
        this.numTurma = numTurma;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
     // Método para adicionar aluno à turma
    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    // Método para remover aluno da turma
    public void removerAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }
}
