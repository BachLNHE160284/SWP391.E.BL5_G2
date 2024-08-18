package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet(name = "UserProfile", urlPatterns = {"/userProfile"})
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the current session
        HttpSession session = request.getSession();

        // Get the user's email address from the session
        String email = (String) session.getAttribute("userEmail");

        // If email is not found in the session, handle accordingly
        if (email == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page if not logged in
            return;
        }

        // Initialize UserDAO and fetch user by email
        UserDAO dao = new UserDAO();
        User acc = dao.getUserByEmail(email);

        // Check if the user was found
        if (acc == null) {
            response.sendRedirect("error.jsp"); // Redirect to an error page if the user is not found
            return;
        }

        // Set the user object as a request attribute
        request.setAttribute("acc", acc);

        // Forward the request to UserProfile.jsp to display user details
        request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request if necessary
    }

    @Override
    public String getServletInfo() {
        return "UserProfile Servlet";
    }
}
