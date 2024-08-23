package controller;

import dal.CartDao;
import dal.ServiceDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Service;
import model.User;

@WebServlet(name = "ReservationDetails", urlPatterns = {"/ReservationDetails"})
public class ReservationDetails extends HttpServlet {

    private static final int PAGE_SIZE = 4;  // Number of services per page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        CartDao cartDao = new CartDao();
        List<Cart> cartList = cartDao.getCartByUserId(user.getUser_id());

        ServiceDAO serviceDAO = new ServiceDAO();
        List<Service> allServices = serviceDAO.getServicesByCart(cartList);

        // Calculate the grand total across all services in the cart
        double grandTotal = 0;
        for (Service service : allServices) {
            grandTotal += service.getSale_prices()* service.getQuantity();
        }

        // Pagination logic
        int page = 1;  // Default to first page
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalServices = allServices.size();
        int totalPages = (int) Math.ceil((double) totalServices / PAGE_SIZE);

        int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalServices);

        List<Service> servicesForPage = allServices.subList(startIndex, endIndex);

        // Set attributes for JSP
        request.setAttribute("services", servicesForPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("grandTotal", grandTotal);  // Set the grand total for all pages
        request.getRequestDispatcher("ReservationDetails.jsp").forward(request, response);
    }
}
