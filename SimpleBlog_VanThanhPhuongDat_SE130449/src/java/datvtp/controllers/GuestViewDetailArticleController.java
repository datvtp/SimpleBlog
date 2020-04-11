/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_ArticleDAO;
import datvtp.daos.Tbl_CommentDAO;
import datvtp.dtos.Tbl_ArticleDTO;
import datvtp.dtos.Tbl_CommentDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class GuestViewDetailArticleController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(GuestViewDetailArticleController.class);
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
        
        try {
            int articleId = Integer.parseInt(request.getParameter("txtArticleId"));
            
            Tbl_ArticleDAO articleDAO = new Tbl_ArticleDAO();
            Tbl_ArticleDTO articleDTO = articleDAO.findArticleById(articleId);
            Tbl_CommentDAO commentDAO = new Tbl_CommentDAO();
            List<Tbl_CommentDTO> list = commentDAO.getListCommentByArticleId(articleId);
            
            request.setAttribute("DTO", articleDTO);
            request.setAttribute("LIST", list);
            
        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at GuestViewDetailArticleController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at GuestViewDetailArticleController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at GuestViewDetailArticleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("viewDetailArticle.jsp").forward(request, response);
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
