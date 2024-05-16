package br.com.curso.model;

import java.text.ParseException;

public class AtividadeRealizada {
    private int idatividaderealizada;
    private String confirmacao;
    private Atividade atividade;
    private Aluno aluno;

    public AtividadeRealizada(int idatividaderealizada, String confirmacao, Atividade atividade, Aluno aluno) {
        this.idatividaderealizada = idatividaderealizada;
        this.confirmacao = confirmacao;
        this.atividade = atividade;
        this.aluno = aluno;
    }
    
    public AtividadeRealizada() throws ParseException{
        this.idatividaderealizada = 0;
        this.confirmacao = "N";
       this.atividade = new Atividade();
       this.aluno = Aluno.alunoVazio();
        
    }

    public int getIdatividaderealizada() {
        return idatividaderealizada;
    }

    public void setIdatividaderealizada(int idatividaderealizada) {
        this.idatividaderealizada = idatividaderealizada;
    }

   
    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
}
