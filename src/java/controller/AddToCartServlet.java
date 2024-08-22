///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller;
//
//import dal.CartDao;
//import dal.ServiceDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import model.Cart;
//import model.Service;
//import model.User;
//
///**
// *
// * @author ntung
// */
//@WebServlet(name="AddToCartServlet", urlPatterns={"/AddToCartServlet"})
//public class AddToCartServlet extends HttpServlet {
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AddToCartServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AddToCartServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    } 
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        String id = request.getParameter("id");
//        HttpSession session = request.getSession();
//         User user = (User) session.getAttribute("acc");
//
//        // Check if the user is logged in
//        if (user == null) {
//            // Redirect to the login page with a message
//            session.setAttribute("loginMessage", "Please log in to book a service.");
//            response.sendRedirect("Login.jsp");
//            return;
//        }
//
//        int userId = user.getUser_id(); // Use the logged-in user's ID
//
//        try {
//            CartDao cartDAO = new CartDao();
//            ServiceDAO serviceDAO = new ServiceDAO();
//
//            //int userId = 1;
//            List<Cart> list = cartDAO.getCartByUserId(userId);
//            int pid = Integer.parseInt(id);
//            boolean isExist = false;
//            Cart c = new Cart();
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getService().getService_id()== pid) {
//                    c = list.get(i);
//                    isExist = true;
//                }
//            }
//
//            if (isExist) {
//                c.setQuantity(c.getQuantity() + 1);
//                cartDAO.UpdateCart(c.getUser_id(), c.getQuantity(), c.getService().getService_id());
////                int userId, int quantity, int productID
//            } else {
//                c = new Cart(userId, serviceDAO.getServiceById(pid), 1);
//                cartDAO.AddCart(c);
//            }
//
//            List<Cart> listC = cartDAO.getCartByUserId1(userId);
//            List<Service> s = serviceDAO.getAllService();
//
////            request.setAttribute("ListProduct", p);
//            request.setAttribute("size", listC.size());
//            session.setAttribute("size", listC.size());
////            request.getRequestDispatcher("JSP/ProductList/ProductList.jsp").forward(request, response);
//            response.sendRedirect("ServiceList");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
