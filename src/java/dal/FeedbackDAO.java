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
    
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        
        // Creating a test Feedback object
        Feedback testFeedback = new Feedback();
        testFeedback.setFeedback_img(null); // Assuming no image for now
        testFeedback.setFeedback("Great service!");
        testFeedback.setRate_star(4.5f);
        testFeedback.setService_id(1);
        testFeedback.setUser_id(2);

        // Call the addFeedback method to test it
        dao.addFeedBack(testFeedback);
    }
}
