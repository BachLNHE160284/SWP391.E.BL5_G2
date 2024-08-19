/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import model.Feedback;

/**
 *
 * @author lebac
 */
@WebServlet(name="SubmitFeedback", urlPatterns={"/SubmitFeedback"})
@MultipartConfig
public class SubmitFeedback extends HttpServlet {
   
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
            out.println("<title>Servlet SubmitFeedback</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitFeedback at " + request.getContextPath () + "</h1>");
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
//        processRequest(request, response);
        int serviceId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("serviceId", serviceId);
        request.getRequestDispatcher("addFeedback.jsp").forward(request, response);
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
//        processRequest(request, response);
        FeedbackDAO dao = new FeedbackDAO();
        Part filePart = request.getPart("feedback_img");
        String feedbackText = request.getParameter("feedback");
        float rateStar = Float.parseFloat(request.getParameter("rate_star"));
        int serviceId = Integer.parseInt(request.getParameter("service_id"));
//        int userId = Integer.parseInt(request.getParameter("user_id"));
        int userId = 1;

        // Lấy tên file và đường dẫn upload
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        // Tạo thư mục uploads nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Đường dẫn đầy đủ để lưu file
        String filePath = uploadPath + File.separator + fileName;

        // Ghi file vào thư mục uploads
        filePart.write(filePath);

        Feedback feedback = new Feedback();
        feedback.setFeedback_img("uploads/" + fileName);
        feedback.setFeedback(feedbackText);
        feedback.setRate_star(rateStar);
        feedback.setService_id(serviceId);
        feedback.setUser_id(userId);
        dao.addFeedBack(feedback);
        response.sendRedirect("ServiceManagementServlet");
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
