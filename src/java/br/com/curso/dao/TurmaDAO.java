package br.com.curso.dao;

import br.com.curso.model.Aluno;
import br.com.curso.model.Disciplina;
import br.com.curso.model.Turma;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurmaDAO implements GenericDAO{
    private Connection conexao;
    
    public TurmaDAO() throws Exception{
        conexao = SingleConnection.getConnection();  
    }

    @Override
    
    public Boolean cadastrar(Object objeto){
      Turma oTurma = (Turma) objeto;
      Boolean retorno = false;
      if(oTurma.getIdTurma() == 0){
          retorno = this.inserir(oTurma);
      }else{
         retorno = this.alterar(oTurma);
      }
      return retorno;
    }
    
 

    @Override
    public Boolean inserir(Object objeto) {
    Turma oTurma = (Turma) objeto;
    PreparedStatement stmtTurma = null;
    PreparedStatement stmtTurmaAluno = null;
    String sqlTurma = "INSERT INTO turma(numturma, iddisciplina, situacao) VALUES (?,?,?);";
    String sqlTurmaAluno = "INSERT INTO turmaaluno(idturma, idaluno) VALUES (?,?);";
      
    try {
        conexao.setAutoCommit(false);
        // Inserção da turma
        stmtTurma = conexao.prepareStatement(sqlTurma, Statement.RETURN_GENERATED_KEYS);
        stmtTurma.setString(1, oTurma.getNumTurma());
        stmtTurma.setInt(2, oTurma.getDisciplina().getIdDisciplina());
        stmtTurma.setString(3, oTurma.getSituacao());
        stmtTurma.executeUpdate();
        
        // Obtenção do ID da turma inserida
        ResultSet rs = stmtTurma.getGeneratedKeys();
        int idTurma = -1;
        if (rs.next()) {
            idTurma = rs.getInt(1);
        }
            
        // Inserção dos alunos na turma (turmaaluno)
        stmtTurmaAluno = conexao.prepareStatement(sqlTurmaAluno);
        for (Aluno aluno : oTurma.getAlunos()) {
            stmtTurmaAluno.setInt(1, idTurma);
            stmtTurmaAluno.setInt(2, aluno.getIdAluno());
            stmtTurmaAluno.executeUpdate();
        }
        
        conexao.commit();
        return true;

    } catch (Exception ex) {
        try {
            System.out.println("Problemas ao inserir a turma! Erro: " + ex.getMessage());
            ex.printStackTrace();
            conexao.rollback();

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}



    @Override
    public Boolean alterar(Object objeto) {
    Turma oTurma = (Turma) objeto;
    PreparedStatement stmt = null;
    String sql = "update turma SET numturma=?, iddisciplina=? where idturma=?";
    String sqlAlunoDelete = "DELETE FROM turmaaluno WHERE idturma=?";
    String sqlAlunoInsert = "INSERT INTO turmaaluno (idturma, idaluno) VALUES (?, ?)";
    
    try {
        // Atualiza a turma
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, oTurma.getNumTurma());
        stmt.setInt(2, oTurma.getDisciplina().getIdDisciplina());
        stmt.setInt(3, oTurma.getIdTurma());
        stmt.executeUpdate();
        
        
        // Remove as associações existentes entre turma e aluno
        PreparedStatement stmtTurmaAlunoDelete = conexao.prepareStatement(sqlAlunoDelete);
        
        stmtTurmaAlunoDelete.setInt(1, oTurma.getIdTurma());
        stmtTurmaAlunoDelete.executeUpdate();
        stmtTurmaAlunoDelete.close(); // Fechar o PreparedStatement
      
        
        // Insere as novas associações entre turma e aluno
        
        PreparedStatement stmtTurmaAlunoInsert = conexao.prepareStatement(sqlAlunoInsert);
        for (Aluno aluno : oTurma.getAlunos()) {
        
            stmtTurmaAlunoInsert.setInt(1, oTurma.getIdTurma());
            stmtTurmaAlunoInsert.setInt(2, aluno.getIdAluno());
            stmtTurmaAlunoInsert.executeUpdate();
        }
        stmtTurmaAlunoInsert.close();
        conexao.commit();
        return true;
    } catch (Exception ex) {
        try {
            System.out.println("Problemas ao alterar a Turma! Erro: " + ex.getMessage());
            ex.printStackTrace();
            conexao.rollback();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}


    @Override
    public Boolean excluir(int numero) {
        int idTurma = numero;
        PreparedStatement stmt = null;
        String sql = "update turma set situacao=? where idturma=?";
        try{
            Turma oTurma = (Turma) this.carregar(idTurma);
            stmt = conexao.prepareStatement(sql);
            if(oTurma.getSituacao().equals("A"))
                stmt.setString(1, "I"); 
            else stmt.setString(1, "A");
            stmt.setInt(2, idTurma);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Turma! Erro: "+ex.getMessage());
            try{
                conexao.rollback();
            }catch(SQLException e){
                System.out.println("Erro rollback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
    int idTurma = numero;
    PreparedStatement stmtTurma = null;
    ResultSet rsTurma = null;
    Turma oTurma = null;
    String sqlTurma = "select * from turma where idturma=?";
    try {
        // Consulta para carregar os dados da turma
        stmtTurma = conexao.prepareStatement(sqlTurma);
        stmtTurma.setInt(1, idTurma);
        rsTurma = stmtTurma.executeQuery();
        
       
        while (rsTurma.next()) {
            oTurma = new Turma();
            oTurma.setIdTurma(rsTurma.getInt("idturma"));
            oTurma.setNumTurma(rsTurma.getString("numturma"));
            oTurma.setSituacao(rsTurma.getString("situacao"));
            
            // Carrega a disciplina associada à turma
            DisciplinaDAO oDisciplinaDAO = new DisciplinaDAO();
            oTurma.setDisciplina((Disciplina) oDisciplinaDAO.carregar(rsTurma.getInt("iddisciplina")));
            
            // Consulta para carregar os IDs dos alunos associados à turma
            PreparedStatement stmtAlunos = conexao.prepareStatement("SELECT idaluno FROM turmaaluno WHERE idturma = ?");
            stmtAlunos.setInt(1, oTurma.getIdTurma());
            ResultSet rsAlunos = stmtAlunos.executeQuery();
            
            // Carrega os alunos associados à turma
            AlunoDAO oAlunoDAO = new AlunoDAO();
            while (rsAlunos.next()) {
                int idAluno = rsAlunos.getInt("idaluno");
                Aluno aluno = (Aluno) oAlunoDAO.carregar(idAluno);
                if (aluno != null) {
                    oTurma.getAlunos().add(aluno);
                }
            }
        }
        
        return oTurma;
    } catch (Exception ex) {
        System.out.println("Problemas ao carregar a turma! Erro: " + ex.getMessage());
        ex.printStackTrace();
        return null;
    } finally {
        // Fecha os recursos
        try {
            if (rsTurma != null) {
                rsTurma.close();
            }
            if (stmtTurma != null) {
                stmtTurma.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos! Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


    private List<Aluno> carregarAlunosDaTurma(int idTurma) {
    List<Aluno> alunos = new ArrayList<>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = conexao.prepareStatement("select idaluno FROM turmaaluno WHERE idturma = ?");
        stmt.setInt(1, idTurma);
        rs = stmt.executeQuery();
        while (rs.next()) {
            int idAluno = rs.getInt("idaluno");
            //carregar cada aluno individualmente
            AlunoDAO alunoDAO = new AlunoDAO();
         
            Aluno aluno = (Aluno) alunoDAO.carregar(idAluno);
            if (aluno != null) {
                alunos.add(aluno); // Adicionar o aluno carregado à lista de alunos
            }
            
            
        }
    } catch (Exception ex){
            System.out.println("Problemas ao carregar a turma! Erro:"+ex.getMessage());
        }
            return alunos;
    }

    
    @Override
    public List<Object> listar(){   
        List<Object> resultado = new ArrayList<>();
       
        PreparedStatement stmt = null;
        ResultSet rs= null;
        String sql = "select * From turma";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
            Turma oTurma = new Turma();
            oTurma.setIdTurma(rs.getInt("idturma"));
            oTurma.setNumTurma(rs.getString("numturma"));
            oTurma.setSituacao(rs.getString("situacao"));
            
            // Carregar disciplina associada à turma
            DisciplinaDAO oDisciplinaDAO = new DisciplinaDAO();
            oTurma.setDisciplina((Disciplina) oDisciplinaDAO.carregar(rs.getInt("iddisciplina")));
            
            //Carrega alunos da turma
            List<Aluno> alunos = carregarAlunosDaTurma(rs.getInt("idturma"));
            oTurma.setAlunos(alunos);
            
            
            resultado.add(oTurma);
        }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar turma!Erro :"+ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
