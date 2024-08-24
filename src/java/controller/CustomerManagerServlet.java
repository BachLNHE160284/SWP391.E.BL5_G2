/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author ntung
 */
@WebServlet(name="CustomerManagerServlet", urlPatterns={"/CustomerManagerServlet"})
public class CustomerManagerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerManagerServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerManagerServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        UserDAO dao = new UserDAO();
        String indexPage = request.getParameter("index");
        String search = request.getParameter("search");
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        if (indexPage == null) {
            indexPage = "1";
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "user_id";
        }
        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "ASC";
        }
        int index = Integer.parseInt(indexPage);
        List<User> users = null;
        int count;
        int endPage = 1;

            if (search != null && !search.isEmpty()) {
            try {
                count = dao.countSearch(search);
                endPage = count / 4;
                if (count % 4 != 0) {
                    endPage++;
                }
                users = dao.searchCustomer(search, sortField, sortOrder, index, 4);
            } catch (Exception ex) {
                Logger.getLogger(CustomerManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
            try {
                count = dao.getTotalUserCount();
                endPage = count / 4;
                if (count % 4 != 0) {
                    endPage++;
                }
                users = dao.pagingCustomer(index, sortField, sortOrder);
            } catch (Exception ex) {
                Logger.getLogger(CustomerManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
            request.setAttribute("users", users);
            request.setAttribute("sortField", sortField);
            request.setAttribute("sortOrder", sortOrder);
            request.getRequestDispatcher("CustomerManagement.jsp").forward(request, response);
        } 
    

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
