package br.com.curso.controller.atividaderealizada;

import br.com.curso.dao.AtividadeDAO;
import br.com.curso.dao.AtividadeRealizadaDAO;
import br.com.curso.model.AtividadeRealizada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeRealizadaListar", urlPatterns = {"/AtividadeRealizadaListar"})
public class AtividadeRealizadaListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            AtividadeRealizadaDAO dao = new AtividadeRealizadaDAO();
            List<AtividadeRealizada> atividadesRealizadas = dao.listar();
            request.setAttribute("atividadesrealizadas", atividadesRealizadas);
            request.getRequestDispatcher("/cadastros/atividaderealizada/atividadeRealizada.jsp").forward(request, response);
        }catch (Exception ex){
            System.out.println("Problemas no servlet ao listar"+ "Atividades! Erro: "+ex.getMessage());
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
