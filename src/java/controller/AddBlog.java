/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.CategoryBlogDAO;
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
import java.util.List;
import model.Blog;
import model.Category_Blog;

/**
 *
 * @author ntung
 */
@WebServlet(name = "AddBlog", urlPatterns = {"/AddBlog"})
@MultipartConfig
public class AddBlog extends HttpServlet {

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
            out.println("<title>Servlet AddBlog</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddBlog at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        CategoryBlogDAO ctb = new CategoryBlogDAO();
        List<Category_Blog> cate = ctb.getAllCategoryBlog();
        request.setAttribute("cate", cate);
        request.getRequestDispatcher("AddBlog.jsp").forward(request, response);
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
        BlogDAO blog = new BlogDAO();

        String blogtitle = request.getParameter("title");
        String content = request.getParameter("content");
        //int authorId = Integer.parseInt(request.getParameter("authorId"));
        // Get the authorId from the session
        Integer authorId = (Integer) request.getSession().getAttribute("authorId");
        if (authorId == null) {
            // Handle the case where authorId is not in the session
            response.sendRedirect("Login.jsp");
            return;
        }
        int updatedBy = Integer.parseInt(request.getParameter("updatedBy"));
        String briefInfo = request.getParameter("briefInfo");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        int status = Integer.parseInt(request.getParameter("status"));
        Part filePart = request.getPart("thumbnail");
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

        // Tạo đối tượng Service
        Blog bl = new Blog();
        bl.setTittle(blogtitle);
        bl.setContent(content);
        bl.setAuthor_id(authorId);
        bl.setUpdate_by(updatedBy);
        bl.setBrief_infor(briefInfo);
        bl.setCategory_id(categoryId);
        bl.setStatus(status);
        bl.setThumbnail("uploads/" + fileName);
        blog.addBlog(bl);
        response.sendRedirect("AddBlog");
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
