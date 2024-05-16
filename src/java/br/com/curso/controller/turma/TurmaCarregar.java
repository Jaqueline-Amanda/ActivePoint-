package br.com.curso.controller.turma;

import br.com.curso.dao.AlunoDAO;
import br.com.curso.dao.DisciplinaDAO;
import br.com.curso.dao.TurmaDAO;
import br.com.curso.model.Turma;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TurmaCarregar", urlPatterns = {"/TurmaCarregar"})
public class TurmaCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            int idTurma = Integer.parseInt(request.getParameter("idTurma"));    
            TurmaDAO oTurmaDAO = new TurmaDAO();
            request.setAttribute("turma", oTurmaDAO.carregar(idTurma));
            DisciplinaDAO oDisciplinaDAO = new DisciplinaDAO();
            request.setAttribute("disciplinas", oDisciplinaDAO.listar());
            /*AlunoDAO oAlunoDAO = new AlunoDAO(); //-dessa forma ele traz todos os alunos para alterar e não somente os de TurmaAluno
            request.setAttribute("alunos", oAlunoDAO.listar());*/
            Turma oTurma = (Turma) oTurmaDAO.carregar(idTurma); 
            request.setAttribute("alunos", oTurma.getAlunos());

            request.getRequestDispatcher("/cadastros/turma/turmaCadastrar.jsp").forward(request, response);
            
        }catch(Exception ex){
            System.out.println("Erro carregar turma"+ex.getMessage());
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
