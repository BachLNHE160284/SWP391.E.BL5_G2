package controller;

import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;

/**
 *
 * @author lebac
 */
@WebServlet(name = "ServiceManagementServlet", urlPatterns = {"/ServiceManagementServlet"})
public class ServiceManagementServlet extends HttpServlet {

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
            out.println("<title>Servlet ServiceManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceManagementServlet at " + request.getContextPath() + "</h1>");
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
        ServiceDAO dao = new ServiceDAO();
        String indexPage = request.getParameter("index");
        String search = request.getParameter("search");
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        if (indexPage == null) {
            indexPage = "1";
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "service_id";
        }
        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "ASC";
        }
        int index = Integer.parseInt(indexPage);
        List<Service> services = null;
        int count;
        int endPage = 1;
        
        if (search != null && !search.isEmpty()) {
            try {
                count = dao.countSearch(search);
                endPage = count / 4;
                if (count % 4 != 0) {
                    endPage++;
                }
                services = dao.searchServices(search, sortField, sortOrder, index, 4);
            } catch (Exception ex) {
                Logger.getLogger(ServiceManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                count = dao.getTotalServiceCount();
                endPage = count / 4;
                if (count % 4 != 0) {
                    endPage++;
                }
                services = dao.pagingService(index, sortField, sortOrder);
                System.out.println(services.toString());
            } catch (Exception ex) {
                Logger.getLogger(ServiceManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        List<Service> service = dao.getAllService();
//        request.setAttribute("service", service);
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("services", services);
        request.setAttribute("sortField", sortField);
        request.setAttribute("sortOrder", sortOrder);
        request.getRequestDispatcher("ServiceManager/serviceManagement.jsp").forward(request, response);
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
