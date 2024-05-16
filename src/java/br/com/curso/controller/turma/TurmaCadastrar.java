package br.com.curso.controller.turma;

import br.com.curso.dao.AlunoDAO;
import br.com.curso.dao.DisciplinaDAO;
import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.TurmaDAO;
import br.com.curso.model.Aluno;
import br.com.curso.model.Disciplina;
import br.com.curso.model.Turma;
import java.io.IOException;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TurmaCadastrar", urlPatterns = {"/TurmaCadastrar"})
public class TurmaCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        int idTurma = Integer.parseInt(request.getParameter("idturma"));
        String numTurma = request.getParameter("numturma");
        String situacao = request.getParameter("situacao");
        int idDisciplina = Integer.parseInt(request.getParameter("iddisciplina"));
        // Obtém o JSON da solicitação
        String jsonString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //Map<String, Object> jsonMap = new Gson().fromJson(jsonString, Map.class);
        // Converte o JSON para um objeto Java usando Gson
        Gson gson = new Gson();
    
        //String[] selectedAlunoIDs = request.getParameterValues("idaluno");
        //String[] selectedAlunoIDs = request.getParameterValues("selectedAlunoIDs");/*idaluno = nome do parâmetro passado no html*/
        //int[] selectedAlunoIDs = gson.fromJson(jsonString, int[].class);
        String[] selectedAlunoIDs = request.getParameter("idaluno").split(",");

        // Mostrar o conteúdo do array
       
        // Collect selected student IDs from request parameters
        //aqui não está chegando o id
        List<Integer> alunoIDs = new ArrayList<>();
        if (selectedAlunoIDs != null) {
            for (String id : selectedAlunoIDs) {
                alunoIDs.add(Integer.parseInt(id));
            }
        }

        
        String mensagem = null;
       try{
           
    
            // Defina o idTurma na instância de Turma com o valor correto
            Turma oTurma = new Turma();
            oTurma.setIdTurma(idTurma);
          /*preciso recuperar o idTurma aqui pois se não irá instancia a model e irá inicializar o idTurma como 0
            e ao invés de alterar, irá cadastrar*/
           
           DisciplinaDAO oDisciplina = new DisciplinaDAO();
           oTurma.setDisciplina((Disciplina) oDisciplina.carregar(idDisciplina));
           oTurma.setNumTurma(numTurma);
           oTurma.setSituacao(situacao);
            
           // Associate selected student IDs with the turma object
            AlunoDAO oAluno = new AlunoDAO();
            List<Aluno> alunos = new ArrayList<>();
            for (Integer idAluno : alunoIDs) {
                Aluno aluno = (Aluno) oAluno.carregar(idAluno);
                if (aluno != null) {
                    alunos.add(aluno);
                }
            }
            oTurma.setAlunos(alunos);
           
           
           GenericDAO dao = new TurmaDAO();
           
           if(dao.cadastrar(oTurma)){
              response.getWriter().write("1");
            }else{
               response.getWriter().write("0");
           }
          
        }catch(Exception ex){
            System.out.println("Problemas no servlet ao inserir Turma! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
