package br.com.curso.controller.atividaderealizada;

import br.com.curso.dao.AtividadeDAO;
import br.com.curso.dao.DisciplinaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeRealizadaCarregar", urlPatterns = {"/AtividadeRealizadaCarregar"})
public class AtividadeRealizadaCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            int idAtividade = Integer.parseInt(request.getParameter("idAtividade"));
            AtividadeDAO oAtividadeDAO = new AtividadeDAO();
            request.setAttribute("atividade", oAtividadeDAO.carregar(idAtividade));
            DisciplinaDAO oDisciplinaDAO = new DisciplinaDAO();
            request.setAttribute("disciplinas", oDisciplinaDAO.listar());
            request.getRequestDispatcher("/cadastros/atividade/atividadeCadastrar.jsp").forward(request, response);
        }catch(Exception ex){
            System.out.println("Erro carregar atividade"+ex.getMessage());
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
