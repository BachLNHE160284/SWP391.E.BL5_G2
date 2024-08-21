/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CartDao;
import dal.CategoryDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Category;
import model.Service;
import model.User;

/**
 *
 * @author ntung
 */
@WebServlet(name="ReservationDetails", urlPatterns={"/ReservationDetails"})
public class ReservationDetails extends HttpServlet {
   
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("u");
//        if(u==null||u.getRole_id()!=2){
//        response.sendRedirect("UserLogin");
//        return;
//        }
        String service_idp = request.getParameter("serviceID");
        String uservicep = request.getParameter("uservice");
        String checkp = request.getParameter("check");
        boolean check = false;
        try {
            check = Boolean.parseBoolean(checkp);
        } catch (Exception e) {
        }
        String servicedelete = request.getParameter("servicedelete");
        CategoryDAO cd = new CategoryDAO();
        List<Category> listcataCategory = cd.getAllCategories();
        CartDao d = new CartDao();
        ServiceDAO pd = new ServiceDAO();
        if (service_idp != null) {
            d.Addtocart(u.getUser_id(), Integer.parseInt(service_idp));
        }
        d.UpdateCart(check, uservicep, servicedelete, u.getUser_id());
        List<Cart> lc = d.getALlCartByUserID(u.getUser_id());
        double totals = d.getTotals(lc);
        //List<Service> newservice = pd.GetListLastProduct();
        //request.setAttribute("newproduct", newproduct);
        request.setAttribute("listcart", lc);
        request.setAttribute("totals", totals);
        request.setAttribute("listcatacategory", listcataCategory);
        request.getRequestDispatcher("ReservationDetails.jsp").forward(request, response);
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
        processRequest(request, response);
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
