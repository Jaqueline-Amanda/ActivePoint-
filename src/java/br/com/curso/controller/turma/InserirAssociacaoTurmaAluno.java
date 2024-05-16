package br.com.curso.controller.turma;

import br.com.curso.dao.TurmaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InserirAssociacaoTurmaAluno", urlPatterns = {"/InserirAssociacaoTurmaAluno"})
public class InserirAssociacaoTurmaAluno extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int idTurma = Integer.parseInt(request.getParameter("idturma"));
            int idAluno = Integer.parseInt(request.getParameter("idaluno"));

            TurmaDAO turmaDAO = new TurmaDAO();
            boolean resultado = turmaDAO.inserirAssociacaoTurmaAluno(idTurma, idAluno);

            if (resultado) {
                response.getWriter().write("Associação turma-aluno inserida com sucesso");
            } else {
                response.getWriter().write("Erro ao inserir associação turma-aluno");
            }
        } catch (Exception ex) {
            response.getWriter().write("Erro ao processar a requisição: " + ex.getMessage());
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
