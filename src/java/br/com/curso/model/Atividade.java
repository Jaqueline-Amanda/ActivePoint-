package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Atividade {
    private int idAtividade;
    private String descricao;
    private String situacao;
    private String status;
    private Date dataAtividade;
    private Date dataPrazo;
    private String documento;
    private Disciplina disciplina;
    private Turma turma;
    private int pontuacaomax;
    private int pontuacaofinal;

    public Atividade(int idAtividade, String descricao, String situacao, String status, Date dataAtividade, Date dataPrazo, String documento, Disciplina disciplina,Turma turma, int pontuacaoMax, int pontuacaoFinal) {
        this.idAtividade = idAtividade;
        this.descricao = descricao;
        this.situacao = situacao;
        this.status = status;
        this.dataAtividade = dataAtividade;
        this.dataPrazo = dataPrazo;
        this.documento = documento;
        this.disciplina = disciplina;
        this.turma = turma;
        this.pontuacaomax = pontuacaoMax;
        this.pontuacaofinal = pontuacaoFinal;
    }
    
    public Atividade() throws ParseException{
        this.idAtividade = 0;
        this.descricao = "";
        this.situacao = "A";
        this.status = "N";
        this.dataAtividade = Conversao.dataAtual();
        this.dataPrazo = Conversao.dataAtual();
        this.disciplina = new Disciplina();
        this.turma = new Turma();
        this.pontuacaomax = 0;
        this.pontuacaofinal = 0;
        
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getPontuacaomax() {
        return pontuacaomax;
    }

    public void setPontuacaomax(int pontuacaomax) {
        this.pontuacaomax = pontuacaomax;
    }

    public int getPontuacaofinal() {
        return pontuacaofinal;
    }

    public void setPontuacaofinal(int pontuacaofinal) {
        this.pontuacaofinal = pontuacaofinal;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    
}
