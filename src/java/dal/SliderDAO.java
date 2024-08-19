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
import model.Slider;

/**
 *
 * @author ntung
 */
public class SliderDAO {

    public void addSlider(Slider slider) {
        try {
            Connection con = new DBContext().getConnection();
            String sql = "INSERT INTO slider (slider_img, backlink, status, slider_title, slider_detail, update_by, create_date) "
                    + "VALUES (?, ?, 1, ?, ?, ?, GETDATE())";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, slider.getSlider_img());
            stmt.setString(2, slider.getBacklink());
            stmt.setString(3, slider.getSlider_title());
            stmt.setString(4, slider.getSlider_detail());
            stmt.setInt(5, slider.getUpdate_by());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Slider> getAllSliderWithStatus(int status) {
        List<Slider> list = new ArrayList<>();
        try {
            Connection con = new DBContext().getConnection();
            String sql = "select * from Slider where status = " + status + ";";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider sl = new Slider();
                sl.setSlider_id(rs.getInt(1));
                sl.setSlider_img(rs.getString(2));
                sl.setBacklink(rs.getString(3));
                sl.setSlider_title(rs.getString(5));
                sl.setSlider_detail(rs.getString(6));
                list.add(sl);
            }
        } catch (Exception ex) {

        }
        return list;
    }

//    public static void main(String[] args) {
//        // Create an instance of Slider with sample data
//        Slider slider = new Slider();
//        slider.setSlider_img("sample1_image.jpg");
//        slider.setBacklink("http://example1.com");
//        slider.setStatus(true); // Assuming 1 is active, 0 is inactive
//        slider.setSlider_title("Sample Slider Title 1");
//        slider.setSlider_detail("This is a sample slider detail 1.");
//        slider.setUpdate_by(1); // Assuming user ID 1 is updating this slider
//
//        // Create an instance of SliderDAO and call the addSlider method
//        SliderDAO dao = new SliderDAO();
//        dao.addSlider(slider);
//
//        // Output success message
//        System.out.println("Slider added successfully.");
//    }
    public List<Slider> getPaginatedSliders(int offset, int limit) {
        List<Slider> sliders = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM slider ORDER BY slider_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSlider_id(rs.getInt("slider_id"));
                slider.setSlider_img(rs.getString("slider_img"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setStatus(rs.getInt("status"));
                slider.setSlider_title(rs.getString("slider_title"));
                slider.setSlider_detail(rs.getString("slider_detail"));
                slider.setUpdate_by(rs.getInt("update_by"));
                slider.setCreate_date(rs.getString("create_date"));
                sliders.add(slider);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sliders;
    }

    public int getTotalSliderCount() {
        int count = 0;
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT COUNT(*) FROM slider";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

//    public List<Slider> getSlidersByStatus(int status) {
//        List<Slider> sliders = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM Slider WHERE status = ?";
//            Connection connection = new DBContext().getConnection();
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, status);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Slider slider = new Slider();
//                // Populate the slider object with data from the result set
//                slider.setSlider_id(rs.getInt("slider_id"));
//                slider.setSlider_img(rs.getString("slider_img"));
//                slider.setBacklink(rs.getString("backlink"));
//                slider.setStatus(rs.getInt("status"));
//                slider.setSlider_title(rs.getString("slider_title"));
//                slider.setSlider_detail(rs.getString("slider_detail"));
//                slider.setUpdate_by(rs.getInt("update_by"));
//                slider.setCreate_date(rs.getString("create_date"));
//                sliders.add(slider);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sliders;
//    }
    public boolean updateSliderStatus(int sliderId, int status) {
        try {
            String query = "UPDATE Slider SET status = ? WHERE slider_id = ?";
            Connection connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, sliderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateSlider(String title, String backlink, String img, int status) {
        String query = "UPDATE Slider SET slider_title = ?, backlink = ?, slider_img = ?, status = ? WHERE slider_id = ?";

        try ( Connection connection = new DBContext().getConnection();  PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, backlink);
            ps.setString(3, img);
            ps.setInt(4, status);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
