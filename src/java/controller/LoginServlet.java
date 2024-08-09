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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember");

        //Set cookies: username, password, rememner
        Cookie cu = new Cookie("cusername", email);
        Cookie cp = new Cookie("cpassword", pass);
        Cookie cr = new Cookie("cremember", remember);

        if (remember != null) {
            cu.setMaxAge(60 * 60 * 24 * 365);
            cp.setMaxAge(60 * 60 * 24 * 365);
            cr.setMaxAge(60 * 60 * 24 * 365);

        } else {
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        //Lưu vào browser
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);

        UserDAO dao = new UserDAO();
        User acc = dao.login(email, pass);

        if (acc == null) {

            request.setAttribute("mess", "Account does not exist! Please Sign up! ");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            if (acc.getStatus() == 1) {
                session.setAttribute("acc", acc);
                switch (acc.getRole_id()) {
                    case 1:
                        response.sendRedirect("HomePage.jsp");
                        break;
                    case 2:
                        response.sendRedirect("HomePage.jsp");
                        break;
                    case 3:
                        response.sendRedirect("HomePage.jsp");
                        break;
                    case 4:
                        response.sendRedirect("HomePage.jsp");
                        break;
                    default:
                        response.sendRedirect("login");
                        break;
                }
            } else {
                request.setAttribute("mess", "Your account has been locked! Please contact the admin to unlock your account!");
                request.getRequestDispatcher("/Login.jsp").forward(request, response);
            }
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
