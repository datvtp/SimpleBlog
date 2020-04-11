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
import datvtp.dtos.Tbl_CommentErrorObject;
import datvtp.utils.DateTimeDataType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class MemberCommentController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MemberCommentController.class);

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

            Tbl_CommentDAO commentDAO = new Tbl_CommentDAO();

            HttpSession session = request.getSession();
            String commentContent = request.getParameter("txtContent");
            String author = (String) session.getAttribute("NAME");
            String timeGenerated = DateTimeDataType.getTimeNow();
            String email = (String) session.getAttribute("EMAIL");

            Tbl_CommentErrorObject commentErrorObj = new Tbl_CommentErrorObject();
            boolean valid = true;
            if (commentContent.isEmpty()) {
                commentErrorObj.setCommentContentError("Comment content can't be blank.");
                valid = false;
            } else if (commentContent.length() > 200) {
                commentErrorObj.setCommentContentError("Comment content must be <= 200 characters.");
                valid = false;
            }

            if (valid) {
                if (commentDAO.insert(commentContent, author, timeGenerated, articleId, email)) {
                    Tbl_ArticleDAO articleDAO = new Tbl_ArticleDAO();
                    Tbl_ArticleDTO articleDTO = articleDAO.findArticleById(articleId);
                    List<Tbl_CommentDTO> list = commentDAO.getListCommentByArticleId(articleId);

                    request.setAttribute("DTO", articleDTO);
                    request.setAttribute("LIST", list);
                    url = "memberViewDetailArticle.jsp";
                } else {
                    request.setAttribute("ERROR", "Comment failed.");
                }
            } else {
                Tbl_ArticleDAO articleDAO = new Tbl_ArticleDAO();
                Tbl_ArticleDTO articleDTO = articleDAO.findArticleById(articleId);
                List<Tbl_CommentDTO> list = commentDAO.getListCommentByArticleId(articleId);

                request.setAttribute("DTO", articleDTO);
                request.setAttribute("LIST", list);
                url = "memberViewDetailArticle.jsp";
                request.setAttribute("INVALID", commentErrorObj);
            }

        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at MemberCommentController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at MemberCommentController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at MemberCommentController: " + e.getMessage());
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
