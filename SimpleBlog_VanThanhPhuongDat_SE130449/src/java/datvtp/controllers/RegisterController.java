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
public class RegisterController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "register.jsp";
    private static final String SUCCESS = "emailConfirm.jsp";

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
            String confirm = request.getParameter("txtConfirm");
            String name = request.getParameter("txtName");

            Tbl_UserDAO userDAO = new Tbl_UserDAO();
            Tbl_UserErrorObject userErrorObj = new Tbl_UserErrorObject();
            boolean valid = true;

            // check EMAIL
            if (email.isEmpty()) {
                userErrorObj.setEmailError("Email can't be blank.");
                valid = false;
            } else if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?") || email.length() > 50) {
                userErrorObj.setEmailError("Email must be valid and <= 50 characters.");
                valid = false;
            } else if (userDAO.checkEmailExist(email) != null) {
                userErrorObj.setEmailError("Email is existed.");
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
            // check CONFIRM
            if (!confirm.equals(password)) {
                userErrorObj.setConfirmError("Confirm must match Password.");
                valid = false;
            }
            // check NAME
            if (name.isEmpty()) {
                userErrorObj.setNameError("Name can't be blank.");
                valid = false;
            } else if (name.length() > 50) {
                userErrorObj.setNameError("Name must be <= 50 characters.");
                valid = false;
            }

            if (valid) {
                if (userDAO.insert(email, EncryptSHA.encyptSHA(password), name)) {
                    String code = RandomCode.getRandomCode();
                    HttpSession session = request.getSession();
                    session.setAttribute("EMAIL", email);
                    session.setAttribute("NAME", name);
                    session.setAttribute("CODE", code);
                    MailUtils.sendCodeToMemberMail(code, email);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Register failed.");
                }
            } else {
                request.setAttribute("INVALID", userErrorObj);
                url = INVALID;
            }

        } catch (NoSuchAlgorithmException e) {
            logger.error("ERROR NoSuchAlgorithm at RegisterController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at RegisterController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at RegisterController: " + e.getMessage());
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
            logger.error("ERROR NoSuchAlgorithm at RegisterController: " + e.getMessage());
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
            logger.error("ERROR NoSuchAlgorithm at RegisterController: " + e.getMessage());
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
