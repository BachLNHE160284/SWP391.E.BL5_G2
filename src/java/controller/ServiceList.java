/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Service;

/**
 *
 * @author ntung
 */
@WebServlet(name = "ServiceList", urlPatterns = {"/ServiceList"})
public class ServiceList extends HttpServlet {

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
            out.println("<title>Servlet ServiceList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private static final int PAGE_SIZE = 2;

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
//        ServiceDAO serviceDao = new ServiceDAO();
//        CategoryDAO ctd = new CategoryDAO();
//        List<Service> listService = serviceDao.getAllService();
//        List<Category> listCate = ctd.getAllCategories();
//
//        request.setAttribute("listService", listService);
//        request.setAttribute("listCate", listCate);
        String pageIndexStr = request.getParameter("page");
        int pageIndex = 1;
        if (pageIndexStr != null) {
            pageIndex = Integer.parseInt(pageIndexStr);
        }

        ServiceDAO serviceDAO = new ServiceDAO();
        CategoryDAO ctd = new CategoryDAO();
        List<Category> listCate = ctd.getAllCategories();
        List<Service> services = serviceDAO.getServices(pageIndex, PAGE_SIZE);
        int totalServices = serviceDAO.getTotalServices();
        int totalPages = (int) Math.ceil((double) totalServices / PAGE_SIZE);

        request.setAttribute("listCate", listCate);
        request.setAttribute("services", services);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("ServiceList.jsp").forward(request, response);

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
