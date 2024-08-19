///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import dal.UserDAO;
//import helper.SendMail;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.security.SecureRandom;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.MessagingException;
//import model.User;
//
///**
// *
// * @author Admin
// */
//@WebServlet(name = "AddUserController", urlPatterns = {"/admin-add-user"})
//public class AddUserController extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AddUserController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AddUserController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        UserDAO userDAO = new UserDAO();
//        // Read the request body as a JSON string
//        StringBuilder jsonBuffer = new StringBuilder();
//        String line;
//        while ((line = request.getReader().readLine()) != null) {
//            jsonBuffer.append(line);
//        }
//
//        JsonObject jsonObj = JsonParser.parseString(jsonBuffer.toString()).getAsJsonObject();
//
//        String email = jsonObj.get("email").getAsString();
//        int roleId = jsonObj.get("role").getAsInt();
//        
//        User registerdUser = userDAO.checkRegister(email);
//        if(registerdUser != null){
//            response.getWriter().print("Email already exsist!");
//            return;
//        }
//        // Auto-generate a password
//        String generatedPassword = generatePassword(8);
//        User user = new User();
//        user.setEmail_address(email);
//        user.setPassword(generatedPassword);
//        user.setRole_id(roleId);
//        
//        
//        int result = userDAO.addNewUser(user);
//        if (result == 0) {
//            response.getWriter().print("Error");
//        } else {
//            String messsge = "Your password is : " + generatedPassword;
//            String subject = "[ChildrenCare] New Password";
//            try {
//                SendMail.sendMailContent(email,subject , messsge);
//            } catch (MessagingException ex) {
//                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            response.getWriter().print("1");
//        }
//    }
//
//    // Method to generate a random password
//    private String generatePassword(int length) {
//        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
//        SecureRandom random = new SecureRandom();
//        StringBuilder password = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//            int randomIndex = random.nextInt(charSet.length());
//            password.append(charSet.charAt(randomIndex));
//        }
//
//        return password.toString();
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
