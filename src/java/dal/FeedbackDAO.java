/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import model.Feedback;

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
    
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        dao.deleteFeedBack(3);
    }
}
