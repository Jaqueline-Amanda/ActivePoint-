package br.com.curso.dao;

import br.com.curso.model.Atividade;
import br.com.curso.model.AtividadeRealizada;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeRealizadaDAO {
    private Connection conexao;
    private AtividadeDAO atividadeDAO;
    
    public AtividadeRealizadaDAO() throws Exception{
        conexao = SingleConnection.getConnection();  
    }
    
    
    public List<AtividadeRealizada> listar() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<AtividadeRealizada> atividadesRealizadas = new ArrayList<>();

    String sql = "SELECT idatividade, descricao, pontuacaomax,pontuacaofinal, status, documento FROM atividade  WHERE status ='E'";
     

    try {
        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();

       while (rs.next()) {
            AtividadeRealizada atividadeRealizada = new AtividadeRealizada();
           
            Atividade atividade = new Atividade();
            atividade.setIdAtividade(rs.getInt("idatividade"));
            atividade.setDescricao(rs.getString("descricao"));
            atividade.setPontuacaomax(rs.getInt("pontuacaomax"));
            atividade.setPontuacaofinal(rs.getInt("pontuacaofinal"));
            atividade.setStatus(rs.getString("status"));
            atividade.setDocumento(rs.getString("documento"));

            atividadeRealizada.setAtividade(atividade);
            atividadeRealizada.setIdatividaderealizada(rs.getInt("idatividade"));
            atividadesRealizadas.add(atividadeRealizada);

        }

         return atividadesRealizadas;
    } catch (Exception ex) {
        System.out.println("Problemas ao carregar a Atividade Realizada! Erro: " + ex.getMessage());
        ex.printStackTrace();
        return null; // Retornar null em caso de erro
    }
}
    
    public AtividadeRealizada carregar(int idatividaderealizada) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AtividadeRealizada atividadeRealizada = null;
         String sql = "SELECT ar.idatividaderealizada, ar.confirmacao, ar.idatividade, a.descricao, a.pontuacaomax,a.pontuacaofinal, a.status, a.documento " +
                 "FROM atividaderealizada ar " +
                 "INNER JOIN atividade a ON ar.idatividade = a.idatividade " +
                 "WHERE ar.idatividaderealizada = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idatividaderealizada);
            rs = stmt.executeQuery();

            if (rs.next()) {
                atividadeRealizada = new AtividadeRealizada();

                atividadeRealizada.setIdatividaderealizada(rs.getInt("idatividaderealizada"));
                atividadeRealizada.setConfirmacao(rs.getString("confirmacao"));
                

                Atividade atividade = new Atividade();
                atividade.setIdAtividade(rs.getInt("idatividade"));
                atividade.setDescricao(rs.getString("descricao"));
                atividade.setPontuacaomax(rs.getInt("pontuacaomax"));
                atividade.setPontuacaofinal(rs.getInt("pontuacaofinal"));
                atividade.setStatus(rs.getString("status"));
                atividade.setDocumento(rs.getString("documento"));

                atividadeRealizada.setAtividade(atividade);
            }

            return atividadeRealizada;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar a Atividade Realizada! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return null; // Retornar null em caso de erro
        }
    }
}
   
     /*public Boolean cadastrar(Object objeto){
        AtividadeRealizada oAtividadeRealizada = (AtividadeRealizada) objeto;
        Boolean retorno = false;
        if(oAtividadeRealizada.pontuacao() == 0){
            retorno = this.inserir(oAtividadeRealizada);
        } else {
            retorno = this.alterar(oAtividadeRealizada);
        }
        return retorno;
    }*/
     
    /*public AtividadeRealizada criarAtividadeRealizada(int idAtividade, int pontuacao) {
        Atividade atividade = atividadeDAO.carregar(idAtividade); // Carregar a atividade usando AtividadeDAO

        AtividadeRealizada atividadeRealizada = new AtividadeRealizada();
        atividadeRealizada.setAtividade(atividade);
        atividadeRealizada.setPontuacaoObtida(pontuacao);

        return atividadeRealizada;
    }

    public Boolean inserirPontuacao() {
    AtividadeRealizada atividadeRealizada = AtividadeRealizada();
    PreparedStatement stmt = null;
    String sql = "INSERT INTO atividade_realizada (idatvrealizada, pontuacao) VALUES (?, ?)";
    try {
        
        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, atividadeRealizada.getIdatvrealizada());
        stmt.setInt(2, atividadeRealizada.getPontuacaoObtida());
        stmt.executeUpdate();
        conexao.commit();
        return true;
    } catch (SQLException ex) {
        try {
            System.out.println("Problemas ao inserir pontuação da atividade realizada! Erro: " + ex.getMessage());
            ex.printStackTrace();
            conexao.rollback();
        } catch (SQLException e) {
            System.out.println("Erro ao fazer rollback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    } finally {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}*/

