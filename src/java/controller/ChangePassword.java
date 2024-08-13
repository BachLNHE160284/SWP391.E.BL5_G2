package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;
import model.User;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{10,40}$");

    private boolean isPasswordValid(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean isOldPasswordCorrect(HttpServletRequest request, User acc) {
        String oldPass = request.getParameter("oldpass");
        return acc != null && acc.getPassword().equals(oldPass);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("acc");

        if (acc == null || !isOldPasswordCorrect(request, acc)) {
            request.setAttribute("mess", "Wrong old password");
            request.setAttribute("messType", "error");
            request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
            return;
        }

        String newPass = request.getParameter("newpass");
        String rePass = request.getParameter("repass");

        if (newPass == null || !newPass.equals(rePass)) {
            request.setAttribute("mess", "New password and confirmation password do not match");
            request.setAttribute("messType", "error");
            request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
            return;
        }

        if (!isPasswordValid(newPass)) {
            request.setAttribute("mess", "Password must be 10-40 characters long and include at least one letter and one number");
            request.setAttribute("messType", "error");
            request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
            return;
        }

        UserDAO dao = new UserDAO();
        dao.changePassword(acc.getEmail_address(), newPass);
        request.setAttribute("mess", "Password changed successfully");
        request.setAttribute("messType", "success");
        request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for changing password";
    }
}
