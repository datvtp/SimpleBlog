/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import java.io.IOException;
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
public class MainController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MainController.class);

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String REGISTER = "RegisterController";
    private static final String CONFIRM_EMAIL = "ConfirmEmailController";

    private static final String GUEST_SEARCH = "GuestSearchArticleController";
    private static final String GUEST_VIEW_DETAIL_ARTICLE = "GuestViewDetailArticleController";
    private static final String GUEST_COMMENT = "login.jsp";
    private static final String GUEST_POST = "login.jsp";
    
    private static final String ADMIN_SEARCH = "AdminSearchArticleController";
    private static final String ADMIN_ACCEPT = "AdminAcceptArticleController";
    private static final String ADMIN_DELETE = "AdminDeleteArticleController";

    private static final String MEMBER_SEARCH = "MemberSearchArticleController";
    private static final String MEMBER_VIEW_DETAIL_ARTICLE = "MemberViewDetailArticleController";
    private static final String MEMBER_COMMENT = "MemberCommentController";
    private static final String MEMBER_POST = "memberPostArticle.jsp";
    private static final String MEMBER_POST_ARTICLE = "MemberPostArticleController";

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
            int role = 0;
            if (session.getAttribute("ROLE") != null) {
                role = Integer.parseInt(session.getAttribute("ROLE").toString());
            }

            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Register")) {
                url = REGISTER;
            } else if (action.equals("Confirm Email")) {
                url = CONFIRM_EMAIL;
            } else if (action.equals("Search") && role == 0) {
                url = GUEST_SEARCH;
            } else if (action.equals("ViewDetailArticle") && role == 0) {
                url = GUEST_VIEW_DETAIL_ARTICLE;
            } else if (action.equals("Comment") && role == 0) {
                url = GUEST_COMMENT;
            } else if (action.equals("PostArticle") && role == 0) {
                url = GUEST_POST;
            } else if (action.equals("Search") && role == 1) {
                url = ADMIN_SEARCH;
            } else if (action.equals("Accept") && role == 1) {
                url = ADMIN_ACCEPT;
            } else if (action.equals("Delete") && role == 1) {
                url = ADMIN_DELETE;
            } else if (action.equals("Search") && role == 2) {
                url = MEMBER_SEARCH;
            } else if (action.equals("ViewDetailArticle") && role == 2) {
                url = MEMBER_VIEW_DETAIL_ARTICLE;
            } else if (action.equals("Comment") && role == 2) {
                url = MEMBER_COMMENT;
            } else if (action.equals("PostArticle") && role == 2) {
                url = MEMBER_POST;
            } else if (action.equals("Post") && role == 2) {
                url = MEMBER_POST_ARTICLE;
            }

        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at MainController: " + e.getMessage());
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
