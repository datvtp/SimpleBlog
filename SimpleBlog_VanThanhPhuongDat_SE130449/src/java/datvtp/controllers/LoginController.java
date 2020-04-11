/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_UserDAO;
import datvtp.dtos.Tbl_UserErrorObject;
import datvtp.utils.EncryptSHA;
import datvtp.utils.MailUtils;
import datvtp.utils.RandomCode;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "login.jsp";
    private static final String ADMIN = "MainController?action=Search&txtSearch=&txtPageNumber=1&txtStatusId=0";
    private static final String MEMBER = "MainController?action=Search&txtSearch=&txtPageNumber=1";
    private static final String CONFIRM = "emailConfirm.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.NoSuchAlgorithmException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {

            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            Tbl_UserErrorObject userErrorObj = new Tbl_UserErrorObject();
            boolean valid = true;

            // check EMAIL
            if (email.isEmpty()) {
                userErrorObj.setEmailError("Email can't be blank.");
                valid = false;
            } else if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?") || email.length() > 50) {
                userErrorObj.setEmailError("Email must be valid and <= 50 characters.");
                valid = false;
            }
            // check PASSWORD
            if (password.isEmpty()) {
                userErrorObj.setPasswordError("Password can't be blank.");
                valid = false;
            } else if (!password.matches("^[a-zA-Z0-9]{5,20}$")) {
                userErrorObj.setPasswordError("Password must be 5-20 characters and mustn't have space or special characters.");
                valid = false;
            }

            if (valid) {
                Tbl_UserDAO userDAO = new Tbl_UserDAO();
                int role = userDAO.checkLogin(email, EncryptSHA.encyptSHA(password));
                if (role == 0) {
                    url = INVALID;
                    userErrorObj.setPasswordError("Invalid Email or Password.");
                    request.setAttribute("INVALID", userErrorObj);
                } else {
                    HttpSession session = request.getSession();
                    int status = userDAO.getStatus();
                    session.setAttribute("EMAIL", email);
                    session.setAttribute("NAME", userDAO.getName());
                    if (role == 1 && status == 2) {
                        session.setAttribute("ROLE", role);
                        url = ADMIN;
                    }
                    if (role == 2 && status == 2) {
                        session.setAttribute("ROLE", role);
                        url = MEMBER;
                    }
                    if (role == 2 && status == 1) {
                        String code = RandomCode.getRandomCode();
                        session.setAttribute("CODE", code);
                        MailUtils.sendCodeToMemberMail(code, email);
                        url = CONFIRM;
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", userErrorObj);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("ERROR NoSuchAlgorithm at LoginController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at LoginController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at LoginController: " + e.getMessage());
        } finally {
            if (url.equals(ERROR) || url.equals(INVALID)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            logger.error("ERROR NoSuchAlgorithm at LoginController: " + e.getMessage());
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            logger.error("ERROR NoSuchAlgorithm at LoginController: " + e.getMessage());
        }
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
