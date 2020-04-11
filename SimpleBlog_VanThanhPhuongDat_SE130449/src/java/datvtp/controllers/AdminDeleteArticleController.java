/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_ArticleDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
public class AdminDeleteArticleController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminDeleteArticleController.class);

    private static final String ERROR = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int articleId = Integer.parseInt(request.getParameter("txtArticleId"));

            String searchTitle = request.getParameter("txtSearch");
            int pageNumber = Integer.parseInt(request.getParameter("txtPageNumber"));
            int statusId = Integer.parseInt(request.getParameter("txtStatusId"));

            Tbl_ArticleDAO articleDAO = new Tbl_ArticleDAO();
            if (articleDAO.update(articleId, 3)) {
                url = "MainController?action=Search"
                        + "&txtSearch=" + searchTitle
                        + "&txtPageNumber=" + pageNumber
                        + "&txtStatusId=" + statusId;
            } else {
                request.setAttribute("ERROR", "Delete article failed.");
            }
        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at AdminDeleteArticleController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at AdminDeleteArticleController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at AdminDeleteArticleController: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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