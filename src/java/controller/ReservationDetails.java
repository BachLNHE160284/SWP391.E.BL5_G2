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
        List<Service> services = serviceDAO.getServicesByCart(cartList);

        request.setAttribute("services", services);
        request.getRequestDispatcher("ReservationDetails.jsp").forward(request, response);
    }
}
