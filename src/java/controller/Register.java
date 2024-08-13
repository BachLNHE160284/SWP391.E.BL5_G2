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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;

public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));

        // Tạo một đối tượng AccountDAO để thực hiện các phương thức xử lý tài khoản
        UserDAO accountDAO = new UserDAO();
        User existingAccount = accountDAO.checkRegister(email);
        if (existingAccount != null) {
            // Chuyển hướng người dùng lại trang đăng ký với thông báo lỗi
            request.setAttribute("messR", "Account exist !");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            accountDAO.registerCustomer(fullname, gender, email,password);
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
//        } else {
//            // Tạo mã xác nhận ngẫu nhiên
//            String code = generateRandomCode();
//            // Gửi mã xác nhận tới email
//            SendMail sendMail = new SendMail();
//            try {
//                sendMail.sendMail(email, code);
//            } catch (MessagingException ex) {
//                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            // Chuyển hướng đến trang nhập mã xác nhận
//            request.setAttribute("email", email);
//            request.setAttribute("fullname", fullname);
//            request.setAttribute("password", password);
//            request.setAttribute("gender", gender);
//            request.setAttribute("code", code);
//            RequestDispatcher rd = request.getRequestDispatcher("CheckCodeRegister.jsp");
//            rd.forward(request, response);
//        }
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
