/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Service;
import model.User;

/**
 *
 * @author lebac
 */
public class FeedbackDAO extends DBContext {

    //BACHLNHE160284
    public void addFeedBack(Feedback p) {
        String sql = "INSERT INTO Feedback (feedback_img, feedback, rate_star, service_id, user_id, feedback_status, create_date) VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getFeedback_img());
            ps.setString(2, p.getFeedback());
            ps.setFloat(3, p.getRate_star());
            ps.setInt(4, p.getService_id());
            ps.setInt(5, p.getUser_id());
            ps.setInt(6, p.getFeedback_status());
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

    //BACHLNHE160284
    public Feedback getFeedBackByID(int feedbackId) throws Exception {
        Feedback feedback = null;
        String sql = "SELECT fb.feedback_id, fb.feedback_img, fb.feedback, fb.rate_star, fb.service_id, fb.user_id, "
                + "fb.feedback_status, fb.create_date, "
                + "u.user_id, u.fullname, u.email_address, "
                + "s.service_id, s.name_service "
                + "FROM feedback fb "
                + "JOIN [user] u ON fb.user_id = u.user_id "
                + "JOIN service s ON fb.service_id = s.service_id "
                + "WHERE fb.feedback_id = ?";

        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
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
                feedback.setFeedback_status(rs.getInt("feedback_status"));
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

    //BACHLNHE160284
    public int countSearch(String search) {
        String sql = "select count(*) from feedback where feedback like ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //BACHLNHE160284
    public List<Feedback> searchFeedback(String searchTerm, String sortField, String sortOrder, int page, int pageSize) throws Exception {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.feedback_id, f.feedback_img, f.feedback, f.rate_star, f.feedback_status, "
                + "f.create_date, s.service_id, s.name_service, u.user_id, u.fullname "
                + "FROM feedback f "
                + "INNER JOIN service s ON f.service_id = s.service_id "
                + "INNER JOIN [user] u ON f.user_id = u.user_id "
                + "WHERE f.feedback LIKE ? "
                + "ORDER BY " + sortField + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedback_id(rs.getInt("feedback_id"));
                    feedback.setFeedback_img(rs.getString("feedback_img"));
                    feedback.setFeedback(rs.getString("feedback"));
                    feedback.setRate_star(rs.getFloat("rate_star"));
                    feedback.setFeedback_status(rs.getInt("feedback_status"));
                    feedback.setCreate_date(rs.getString("create_date"));

                    Service service = new Service();
                    service.setService_id(rs.getInt("service_id"));
                    service.setName_service(rs.getString("name_service"));
                    feedback.setService(service);

                    User user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setFullname(rs.getString("fullname"));
                    feedback.setUser(user);

                    feedbacks.add(feedback);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return feedbacks;
    }

    //BACHLNHE160284
    public int getTotalFeedbackCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM feedback";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //BACHLNHE160284
    public List<Feedback> pagingFeedback(int pageIndex, String sortField, String sortOrder) throws SQLException, Exception {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT f.*, u.fullname, s.name_service FROM feedback f "
                + "JOIN [user] u ON f.user_id = u.user_id "
                + "JOIN service s ON f.service_id = s.service_id "
                + "ORDER BY " + sortField + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, (pageIndex - 1) * 4);
            ps.setInt(2, 4);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedback_id(rs.getInt("feedback_id"));
                    feedback.setFeedback_img(rs.getString("feedback_img"));
                    feedback.setFeedback(rs.getString("feedback"));
                    feedback.setRate_star(rs.getFloat("rate_star"));
                    feedback.setFeedback_status(rs.getInt("feedback_status"));
                    feedback.setCreate_date(rs.getString("create_date"));

                    // Set user details
                    User user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setFullname(rs.getString("fullname"));
                    feedback.setUser(user);

                    // Set service details
                    Service service = new Service();
                    service.setService_id(rs.getInt("service_id"));
                    service.setName_service(rs.getString("name_service"));
                    feedback.setService(service);

                    list.add(feedback);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();

            int a = feedbackDAO.getTotalFeedbackCount();
            System.out.println(a);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
