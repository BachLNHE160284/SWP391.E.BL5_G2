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
@WebServlet(name = "SearchService", urlPatterns = {"/SearchService"})
public class SearchService extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServiceDAO serviceDAO;

    @Override
    public void init() {
        serviceDAO = new ServiceDAO();
    }

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
//        // Lấy các tham số từ request
//        String keyword = request.getParameter("keyword");
//        String pageStr = request.getParameter("page");
//
//        int page = 1;
//        int pageSize = 10; // Số lượng dịch vụ hiển thị trên một trang
//
//        // Kiểm tra và parse giá trị của tham số "page" nếu không null
//        if (pageStr != null) {
//            try {
//                page = Integer.parseInt(pageStr);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//
//        int offset = (page - 1) * pageSize;
//
//        // Gọi phương thức searchAndPaginateServices từ DAO
//        List<Service> services = serviceDAO.searchAndPaginateServices(keyword, offset, pageSize);
//
//        // Đếm tổng số bản ghi để tính tổng số trang
//        int totalRecords = serviceDAO.countServices(keyword);
//        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
//
//        // Set attributes để gửi về JSP
//        request.setAttribute("services", services);
//        request.setAttribute("totalPages", totalPages);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("keyword", keyword);
//
//        // Forward kết quả đến JSP
//        request.getRequestDispatcher("ServiceList.jsp").forward(request, response);
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
    private static final int PAGE_SIZE = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                // Lấy tham số từ request
        String keyword = request.getParameter("keyword");
        String pageIndexStr = request.getParameter("page");
        String cateIdStr = request.getParameter("cateId");

        int pageIndex = 1;
        if (pageIndexStr != null) {
            try {
                pageIndex = Integer.parseInt(pageIndexStr);
            } catch (NumberFormatException e) {
                pageIndex = 1; // Set default page index if parsing fails
            }
        }

        ServiceDAO serviceDAO = new ServiceDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        List<Category> listCate = categoryDAO.getAllCategories();
        List<Service> services;
        int totalServices;

        // Tìm kiếm theo từ khóa và phân trang
        if (keyword != null && !keyword.trim().isEmpty()) {
            services = serviceDAO.searchAndPaginateServices(keyword, (pageIndex - 1) * PAGE_SIZE, PAGE_SIZE);
            totalServices = serviceDAO.countSearch(keyword);
        } else if (cateIdStr != null && !cateIdStr.isEmpty()) {
            int cateId = Integer.parseInt(cateIdStr);
            services = serviceDAO.getServicesByCategoryId(cateId, pageIndex, PAGE_SIZE);
            totalServices = serviceDAO.getTotalServicesByCategoryId(cateId);
            request.setAttribute("selectedCateId", cateId); // Để theo dõi danh mục đã chọn
        } else {
            services = serviceDAO.getServices(pageIndex, PAGE_SIZE);
            totalServices = serviceDAO.getTotalServices();
        }

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
