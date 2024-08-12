/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import helper.SendMail;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;



/**
 *
 * @author Admin
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the ForgotPassword.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("ForgotPassword.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form quên mật khẩu
        String email = request.getParameter("email");
        UserDAO accoutDAO = new UserDAO();
        User user = accoutDAO.checkRegister(email);
        if (user == null) {
            request.setAttribute("message", "Không thể gửi mã xác nhận đến email chưa đăng ký. Vui lòng thử lại sau.");
            // Forward lại trang ForgotPassword.jsp để hiển thị thông báo
            RequestDispatcher dispatcher = request.getRequestDispatcher("ForgotPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            // Tạo mã xác nhận ngẫu nhiên
            String code = generateRandomCode();
            // Gửi mã xác nhận tới email
            SendMail sendMail = new SendMail();
            try {
                sendMail.sendMail(email,code);
            } catch (MessagingException ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Chuyển hướng đến trang nhập mã xác nhận
            request.setAttribute("email", email);
            request.setAttribute("code", code);
            RequestDispatcher rd = request.getRequestDispatcher("EnterConfirmationCode.jsp");
            rd.forward(request, response);

        }
    }

    // Hàm tạo mã xác nhận ngẫu nhiên
    private String generateRandomCode() {
        // Độ dài của mã xác nhận
        int codeLength = 6;

        // Mảng chứa ký tự cho việc tạo mã xác nhận
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Tạo một đối tượng StringBuilder để xây dựng mã xác nhận
        StringBuilder sb = new StringBuilder(codeLength);

        // Tạo một SecureRandom object để tạo số ngẫu nhiên
        SecureRandom random = new SecureRandom();

        // Tạo mã xác nhận ngẫu nhiên bằng cách chọn ngẫu nhiên các ký tự từ chuỗi characters
        for (int i = 0; i < codeLength; i++) {
            // Chọn một ký tự ngẫu nhiên từ chuỗi characters
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            // Thêm ký tự ngẫu nhiên vào StringBuilder
            sb.append(randomChar);
        }

        // Trả về mã xác nhận đã tạo
        return sb.toString();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
