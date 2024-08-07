/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Category;
import model.Service;

/**
 *
 * @author lebac
 */
public class ServiceDAO extends DBContext{
    
    public void addService(Service service) {
        String sql = "INSERT INTO service (name_service, original_prices, sale_prices, quantity, category_id, thumbnail, " +
                     "brief_infor, service_detail, img_service, date_add, service_Status, create_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), 1, GETDATE())";
        try (
             PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, service.getName_service());
            ps.setFloat(2, service.getOriginal_prices());
            ps.setFloat(3, service.getSale_prices());
            ps.setInt(4, service.getQuantity());
            ps.setInt(5, service.getCategopry().getCategory_id());
            ps.setString(6, service.getThumbnail());
            ps.setString(7, service.getBrief_infor());
            ps.setString(8, service.getService_detail());
            ps.setString(9, service.getImg_service());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        ServiceDAO dao = new ServiceDAO();
        
        // Create a Category object
        Category category = new Category();
        category.setCategory_id(1);
        

        // Create a Service object and set its properties
        Service service = new Service();
        service.setName_service("Test Service");
        service.setOriginal_prices(100.0f);
        service.setSale_prices(80.0f);
        service.setQuantity(10);
//        service.setCategory(category);
        service.setCategopry(category); 
        service.setThumbnail("thumbnail.jpg");
        service.setBrief_infor("Brief Info");
        service.setService_detail("Service Detail");
        service.setImg_service("img_service.jpg");

        // Add the service using the DAO
        dao.addService(service);
        System.out.println("Service added successfully.");
    }
    
}
