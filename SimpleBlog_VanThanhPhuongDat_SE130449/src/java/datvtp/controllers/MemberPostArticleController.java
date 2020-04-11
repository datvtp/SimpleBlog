/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_ArticleDAO;
import datvtp.dtos.Tbl_ArticleErrorObject;
import datvtp.utils.DateTimeDataType;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
public class MemberPostArticleController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MemberPostArticleController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "MainController?action=Search&txtSearch=&txtPageNumber=1";
    private static final String INVALID = "memberPostArticle.jsp";

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
            HttpSession session = request.getSession();
            String title = request.getParameter("txtTitle");
            String shortDescription = request.getParameter("txtShortDescription");
            String content = request.getParameter("txtContent");
            String author = (String) session.getAttribute("NAME");
            String time = DateTimeDataType.getTimeNow();
            String email = (String) session.getAttribute("EMAIL");

            Tbl_ArticleErrorObject articleErrorObj = new Tbl_ArticleErrorObject();
            boolean valid = true;

            // check title
            if (title.isEmpty()) {
                articleErrorObj.setTitleError("Title can't be blank.");
                valid = false;
            } else if (title.length() > 50) {
                articleErrorObj.setTitleError("Title must be <= 50 characters.");
                valid = false;
            }

            // check short description
            if (shortDescription.isEmpty()) {
                articleErrorObj.setShortDescriptionError("Short description can't be blank.");
                valid = false;
            } else if (shortDescription.length() > 100) {
                articleErrorObj.setShortDescriptionError("Short description must be <= 100 characters.");
                valid = false;
            }

            // check content
            if (content.isEmpty()) {
                articleErrorObj.setContentError("Content can't be blank.");
                valid = false;
            } else if (content.length() > 500) {
                articleErrorObj.setContentError("Content must be <= 500 characters.");
                valid = false;
            }
            
            if (valid) {
                Tbl_ArticleDAO articleDAO = new Tbl_ArticleDAO();
                if (articleDAO.insert(title, shortDescription, content, author, time, 1, email)) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Post article failed.");
                }
            } else {
                request.setAttribute("INVALID", articleErrorObj);
                url = INVALID;
            }

        } catch (SQLException e) {
            logger.error("ERROR SQL at MemberPostArticleController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at MemberPostArticleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
