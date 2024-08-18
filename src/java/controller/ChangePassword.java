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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to changePassword.jsp to display the form
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String oldPassword = request.getParameter("oldpass");
        String newPassword = request.getParameter("newpass");
        String rePassword = request.getParameter("repass");

        // Get email from the session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            // Handle case where email is not available in session (user might not be logged in)
            request.setAttribute("messType", "error");
            request.setAttribute("mess", "User is not logged in.");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            // Check if the new passwords match
            if (!newPassword.equals(rePassword)) {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "New passwords do not match.");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            // Retrieve the current user to verify old password
            User currentUser = userDAO.getUserByEmail(email);
            if (currentUser == null || !currentUser.getPassword().equals(oldPassword)) {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "Old password is incorrect.");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            // Update the password
            boolean isUpdated = userDAO.updateByEmail(email, newPassword);
            if (isUpdated) {
                request.setAttribute("messType", "success");
                request.setAttribute("mess", "Password successfully changed.");
                // Optionally, you might want to refresh user data in session
                User updatedUser = userDAO.getUserByEmail(email);
                session.setAttribute("userEmail", updatedUser.getEmail_address()); // Update session with new user data
                session.setAttribute("currentUser", updatedUser); // If you have a User object in session
            } else {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "Failed to change password. Please try again.");
            }

            // Forward to UserProfile.jsp to display the updated profile
            request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("messType", "error");
            request.setAttribute("mess", "An error occurred. Please try again later.");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }
    }
}
