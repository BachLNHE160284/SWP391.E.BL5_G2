/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Slider;

/**
 *
 * @author ntung
 */
public class SliderDAO {
    public void addSlider(Slider slider) {
        try {
            Connection con = new DBContext().getConnection();
            String sql = "INSERT INTO slider (slider_img, backlink, status, slider_title, slider_detail, update_by, create_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, slider.getSlider_img());
            stmt.setString(2, slider.getBacklink());
            stmt.setBoolean(3, slider.isStatus());
            stmt.setString(4, slider.getSlider_title());
            stmt.setString(5, slider.getSlider_detail());
            stmt.setInt(6, slider.getUpdate_by());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public static void main(String[] args) {
        // Create an instance of Slider with sample data
        Slider slider = new Slider();
        slider.setSlider_img("sample1_image.jpg");
        slider.setBacklink("http://example1.com");
        slider.setStatus(true); // Assuming 1 is active, 0 is inactive
        slider.setSlider_title("Sample Slider Title 1");
        slider.setSlider_detail("This is a sample slider detail 1.");
        slider.setUpdate_by(1); // Assuming user ID 1 is updating this slider

        // Create an instance of SliderDAO and call the addSlider method
        SliderDAO dao = new SliderDAO();
        dao.addSlider(slider);

        // Output success message
        System.out.println("Slider added successfully.");
    }
}
