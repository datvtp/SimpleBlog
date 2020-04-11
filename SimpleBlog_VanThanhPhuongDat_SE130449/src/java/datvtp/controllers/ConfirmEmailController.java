/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_UserDAO;
import datvtp.dtos.Tbl_UserErrorObject;
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
public class ConfirmEmailController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ConfirmEmailController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "MainController?action=Search&txtSearch=&txtPageNumber=1";
    private static final String INVALID = "emailConfirm.jsp";

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
            String code = (String) session.getAttribute("CODE");
            String codeInput = request.getParameter("txtCode");

            Tbl_UserErrorObject userErrorObj = new Tbl_UserErrorObject();
            boolean valid = true;
            
            if (codeInput.isEmpty()) {
                userErrorObj.setCodeEmailError("Verify code can't be blank.");
                valid = false;
            }

            if (valid) {
                if (codeInput.equals(code)) {
                    Tbl_UserDAO userDAO = new Tbl_UserDAO();
                    String email = (String) session.getAttribute("EMAIL");
                    userDAO.updateStatusUser(email, 2);
                    session.setAttribute("ROLE", "2");
                    url = SUCCESS;
                } else {
                    userErrorObj.setCodeEmailError("Invalid verify code.");
                    request.setAttribute("INVALID", userErrorObj);
                    url = INVALID;
                }
            } else {
                request.setAttribute("INVALID", userErrorObj);
                url = INVALID;
            }
        } catch (SQLException e) {
            logger.error("ERROR SQL at ConfirmEmailController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at ConfirmEmailController: " + e.getMessage());
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
