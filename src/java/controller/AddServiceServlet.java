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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Service;

/**
 *
 * @author lebac
 */
@WebServlet(name="AddServiceServlet", urlPatterns={"/AddServiceServlet"})
@MultipartConfig
public class AddServiceServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AddServiceServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServiceServlet at " + request.getContextPath () + "</h1>");
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
        try {
            CategoryDAO dao = new CategoryDAO();
            List<Category> categories = dao.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("ServiceManager/addService.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            // Lấy thông tin từ form
            String nameService = request.getParameter("name_service");
            float originalPrices = Float.parseFloat(request.getParameter("original_prices"));
            float salePrices = Float.parseFloat(request.getParameter("sale_prices"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String thumbnail = request.getParameter("thumbnail");
            String briefInfo = request.getParameter("brief_infor");
            String serviceDetail = request.getParameter("service_detail");
            Part filePart = request.getPart("img_service");
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
            Service service = new Service();
            service.setName_service(nameService);
            service.setOriginal_prices(originalPrices);
            service.setSale_prices(salePrices);
            service.setQuantity(quantity);
            service.setCategopry(new Category(categoryId)); // assuming Category constructor with ID
            service.setThumbnail(thumbnail);
            service.setBrief_infor(briefInfo);
            service.setService_detail(serviceDetail);
            service.setImg_service("uploads/" + fileName);

            // Thêm dịch vụ vào cơ sở dữ liệu
            ServiceDAO serviceDAO = new ServiceDAO();
            serviceDAO.addService(service);

            // Chuyển hướng đến trang danh sách dịch vụ hoặc thông báo thành công
            response.sendRedirect("ServiceManager/serviceManagement.jsp");
        } catch (NumberFormatException e) {
            Logger.getLogger(AddServiceServlet.class.getName()).log(Level.SEVERE, "Invalid number format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format.");
        } catch (ServletException e) {
            Logger.getLogger(AddServiceServlet.class.getName()).log(Level.SEVERE, "Servlet exception", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Servlet exception.");
        } catch (IOException e) {
            Logger.getLogger(AddServiceServlet.class.getName()).log(Level.SEVERE, "IO exception", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "IO exception.");
        } catch (Exception e) {
            Logger.getLogger(AddServiceServlet.class.getName()).log(Level.SEVERE, "Unexpected error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error.");
        }
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
