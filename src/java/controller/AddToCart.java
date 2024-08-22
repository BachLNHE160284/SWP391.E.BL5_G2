package controller;

import dal.CartDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Cart;
import model.User;

@WebServlet(name="AddToCart", urlPatterns={"/AddToCart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("ReservationDetails.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("ReservationDetails.jsp");
            return;
        }

        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartDao cartDao = new CartDao();
        cartDao.Addtocart(user.getUser_id(), serviceID, quantity);

        response.sendRedirect("ReservationDetails.jsp");
    }
}
