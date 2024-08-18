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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the ForgotPassword.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("changePassword.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("oldpass");
        String newPassword = request.getParameter("newpass");
        String rePassword = request.getParameter("repass");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("acc");

        if (currentUser == null) {
            request.setAttribute("messType", "error");
            request.setAttribute("mess", "User is not logged in.");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        String email = currentUser.getEmail_address();

        UserDAO userDAO = new UserDAO();
        try {
            if (!newPassword.equals(rePassword)) {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "New passwords do not match.");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            User user = userDAO.getUserByEmail(email);
            if (user == null || !user.getPassword().equals(oldPassword)) {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "Old password is incorrect.");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            boolean isUpdated = userDAO.updateByEmail(email, newPassword);
            if (isUpdated) {
                request.setAttribute("messType", "success");
                request.setAttribute("mess", "Password successfully changed.");
                User updatedUser = userDAO.getUserByEmail(email);
                session.setAttribute("acc", updatedUser);
            } else {
                request.setAttribute("messType", "error");
                request.setAttribute("mess", "Failed to change password. Please try again.");
            }

            request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("messType", "error");
            request.setAttribute("mess", "An error occurred. Please try again later.");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }
    }
}
