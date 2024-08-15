/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.CategoryBlogDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Blog;
import model.Category_Blog;
import model.User;

/**
 *
 * @author lebac
 */
@WebServlet(name = "UpdatePostServlet", urlPatterns = {"/UpdatePostServlet"})
@MultipartConfig
public class UpdatePostServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdatePostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePostServlet at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDAO dao = new BlogDAO();
        CategoryBlogDAO cateb = new CategoryBlogDAO();
        UserDAO user = new UserDAO();
        List<User> u = user.getAllUser();
        List<Category_Blog> cate = cateb.getAllCategoryBlog();
        Blog blog = dao.getBlogByID(id);
        request.setAttribute("u", u);
        request.setAttribute("cate", cate);
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("PostManager/updatePost.jsp").forward(request, response);
        
        
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
//        processRequest(request, response);
        // Lấy dữ liệu từ form
        int blogId = Integer.parseInt(request.getParameter("blog_id"));
        String title = request.getParameter("tittle");
        String content = request.getParameter("content");
        int authorId = Integer.parseInt(request.getParameter("author_id"));
//        int updatedBy = Integer.parseInt(request.getParameter("updated_by"));
//        String thumbnail = request.getParameter("thumbnail");
        String briefInfo = request.getParameter("brief_infor");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        int status = Integer.parseInt(request.getParameter("status"));
        
        Part imgPart = request.getPart("img_service");
            String imgService;
            if (imgPart != null && imgPart.getSize() > 0) {
                imgService = "uploads/" + imgPart.getSubmittedFileName();
                imgPart.write(getServletContext().getRealPath("/") + imgService);
            } else {
                imgService = request.getParameter("currentImagePath"); // Nếu không có ảnh mới, giữ nguyên ảnh cũ
            }
        
        
        

        // Tạo đối tượng Blog với dữ liệu từ form
        Blog blog = new Blog();
        blog.setBlog_id(blogId);
        blog.setTittle(title);
        blog.setContent(content);
        blog.setAuthor_id(authorId);
//        blog.setUpdate_by(updatedBy);
        blog.setThumbnail(imgService);
        blog.setBrief_infor(briefInfo);
        blog.setCategory_id(categoryId);
        blog.setStatus(status);

        // Sử dụng BlogDAO để cập nhật blog
        BlogDAO blogDAO = new BlogDAO();
        blogDAO.updateBlog(blog);

        // Điều hướng về trang quản lý blog hoặc hiển thị thông báo
        response.sendRedirect("PostManagementServlet");
    

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
