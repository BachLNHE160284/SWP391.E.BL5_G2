package controller;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet(name = "UpdateProfile", urlPatterns = {"/updateProfile"})
public class UpdateProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the updateProfile.jsp page to show the update form
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateProfile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            response.sendRedirect("UserProfile.jsp");
            return;
        }

        // Retrieve form parameters
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String fullname = request.getParameter("fullname");
        String phone_number = request.getParameter("phone_number");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Optional
        String avartar = request.getParameter("avartar");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        int status = Integer.parseInt(request.getParameter("status"));
        String create_date = request.getParameter("create_date"); // Optional

        User user = new User();
        user.setUser_id(user_id);
        user.setEmail_address(email);
        user.setFullname(fullname);
        user.setPhone_number(phone_number);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password); // Optional
        user.setAvartar(avartar);
        user.setGender(gender);
        user.setRole_id(role_id);
        user.setStatus(status);
        user.setCreate_date(create_date); // Optional

        UserDAO dao = new UserDAO();
        dao.updateUser(fullname, gender, phone_number, address, username, avartar, role_id, status, address);
        request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
    }
}
