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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Service;

/**
 *
 * @author lebac
 */
@WebServlet(name = "UpdateServiceServlet", urlPatterns = {"/UpdateServiceServlet"})
@MultipartConfig
public class UpdateServiceServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateServiceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServiceServlet at " + request.getContextPath() + "</h1>");
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
        try {
            //        processRequest(request, response);
            int id = Integer.parseInt(request.getParameter("id"));
            ServiceDAO dao = new ServiceDAO();
            CategoryDAO cate = new CategoryDAO();
            List<Category> categories = cate.getAllCategories();
            request.setAttribute("categories", categories);
            Service service = dao.getServiceById(id);
            request.setAttribute("service", service);
            request.getRequestDispatcher("ServiceManager/updateService.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UpdateServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
            // Lấy dữ liệu từ form
            int serviceId = Integer.parseInt(request.getParameter("service_id"));
            String nameService = request.getParameter("name_service");
            float originalPrices = Float.parseFloat(request.getParameter("original_prices"));
            float salePrices = Float.parseFloat(request.getParameter("sale_prices"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String thumbnail = request.getParameter("thumbnail");
            String briefInfor = request.getParameter("brief_infor");
            String serviceDetail = request.getParameter("service_detail");

            // Xử lý hình ảnh nếu có cập nhật
            Part imgPart = request.getPart("img_service");
            String imgService;
            if (imgPart != null && imgPart.getSize() > 0) {
                imgService = "uploads/" + imgPart.getSubmittedFileName();
                imgPart.write(getServletContext().getRealPath("/") + imgService);
            } else {
                imgService = request.getParameter("currentImagePath"); // Nếu không có ảnh mới, giữ nguyên ảnh cũ
            }

            int serviceStatus = 1; // Giả sử trạng thái dịch vụ mặc định là 1

            // Tạo đối tượng Service với các thông tin lấy từ form
            Service service = new Service();
            service.setService_id(serviceId);
            service.setName_service(nameService);
            service.setOriginal_prices(originalPrices);
            service.setSale_prices(salePrices);
            service.setQuantity(quantity);

            Category category = new Category();
            category.setCategory_id(categoryId);
            service.setCategory(category);

            service.setThumbnail(thumbnail);
            service.setBrief_infor(briefInfor);
            service.setService_detail(serviceDetail);
            service.setImg_service(imgService);
            service.setService_Status(serviceStatus);

            // Gọi hàm updateService trong DAO để cập nhật thông tin dịch vụ
            ServiceDAO dao = new ServiceDAO();
            dao.updateService(service);

            // Chuyển hướng người dùng về trang quản lý dịch vụ sau khi cập nhật thành công
            response.sendRedirect("ServiceManagementServlet");
        
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
