/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Service;
import model.User;

/**
 *
 * @author lebac
 */
public class FeedbackDAO extends DBContext{
    //BACHLNHE160284
    public void addFeedBack(Feedback p) {
        String sql = "INSERT INTO Feedback (feedback_img, feedback, rate_star, service_id, user_id, create_date) VALUES (?, ?, ?, ?, ?, GETDATE())";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getFeedback_img());
            ps.setString(2, p.getFeedback());
            ps.setFloat(3, p.getRate_star());
            ps.setInt(4, p.getService_id());
            ps.setInt(5, p.getUser_id());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //BACHLNHE160284
    public void deleteFeedBack(int feedBackId) {
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";
        try ( PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, feedBackId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Feedback getFeedBackByID(int feedbackId) throws Exception {
        Feedback feedback = null;
        String sql = "SELECT fb.feedback_id, fb.feedback_img, fb.feedback, fb.rate_star, fb.service_id, fb.user_id, " +
                     "fb.feedback_status, fb.create_date, " +
                     "u.user_id, u.fullname, u.email_address, " +
                     "s.service_id, s.name_service " +
                     "FROM feedback fb " +
                     "JOIN [user] u ON fb.user_id = u.user_id " +
                     "JOIN service s ON fb.service_id = s.service_id " +
                     "WHERE fb.feedback_id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, feedbackId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedback_id(rs.getInt("feedback_id"));
                feedback.setFeedback_img(rs.getString("feedback_img"));
                feedback.setFeedback(rs.getString("feedback"));
                feedback.setRate_star(rs.getFloat("rate_star"));
                feedback.setService_id(rs.getInt("service_id"));
                feedback.setUser_id(rs.getInt("user_id"));
                feedback.setFeedback_status(rs.getBoolean("feedback_status"));
                feedback.setCreate_date(rs.getString("create_date"));

                // Tạo đối tượng User và gán dữ liệu
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail_address(rs.getString("email_address"));
                feedback.setUser(user);

                // Tạo đối tượng Service và gán dữ liệu
                Service service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setName_service(rs.getString("name_service"));
                feedback.setService(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedback;
    }

    
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        try {
            Feedback feedback = dao.getFeedBackByID(4);
            System.out.println(feedback.toString());
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
