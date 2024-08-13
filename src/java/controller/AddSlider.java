/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SliderDAO;
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
import model.Slider;

/**
 *
 * @author ntung
 */
@WebServlet(name = "AddSlider", urlPatterns = {"/AddSlider"})
@MultipartConfig
public class AddSlider extends HttpServlet {

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
            out.println("<title>Servlet AddSlider</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSlider at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AddSlider.jsp").forward(request, response);
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
        SliderDAO slider = new SliderDAO();

        String backlink = request.getParameter("backlink");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String sliderTitle = request.getParameter("sliderTitle");
        String sliderDetail = request.getParameter("sliderDetail");
        int updateBy = Integer.parseInt(request.getParameter("updateBy"));
        Part filePart = request.getPart("sliderImg");
        // Xử lý upload ảnh

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

        // Tạo đối tượng
        Slider sl = new Slider();
        sl.setBacklink(backlink);
        sl.isStatus();
        sl.setSlider_title(sliderTitle);
        sl.setSlider_detail(sliderDetail);
        sl.setUpdate_by(updateBy);
        sl.setSlider_img("uploads/" + fileName);
        slider.addSlider(sl);
        response.sendRedirect("AddSlider");
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
