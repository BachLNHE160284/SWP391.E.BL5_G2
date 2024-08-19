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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Service;

/**
 *
 * @author lebac
 */
public class ServiceDAO extends DBContext {

    //BACHLNHE160284
    public void addService(Service service) {
        String sql = "INSERT INTO service (name_service, original_prices, sale_prices, quantity, category_id, thumbnail, "
                + "brief_infor, service_detail, img_service, date_add, service_Status, create_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), 1, GETDATE())";
        try (
                 PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, service.getName_service());
            ps.setFloat(2, service.getOriginal_prices());
            ps.setFloat(3, service.getSale_prices());
            ps.setInt(4, service.getQuantity());
            ps.setInt(5, service.getCategory().getCategory_id());
            ps.setString(6, service.getThumbnail());
            ps.setString(7, service.getBrief_infor());
            ps.setString(8, service.getService_detail());
            ps.setString(9, service.getImg_service());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //BACHLNHE160284
    public List<Service> getAllService() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT s.service_id, s.name_service, s.original_prices, s.sale_prices, s.quantity, s.thumbnail, s.brief_infor,\n"
                + "    s.service_detail, s.date_add, s.service_Status, s.create_date, s.img_service, c.category_id, c.category_name\n"
                + "FROM service s\n"
                + "INNER JOIN category c ON s.category_id = c.category_id;";

        try (
                 Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Service service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setCategory_id(rs.getInt("category_id"));
                service.setName_service(rs.getString("name_service"));
                service.setOriginal_prices(rs.getFloat("original_prices"));
                service.setSale_prices(rs.getFloat("sale_prices"));
                service.setQuantity(rs.getInt("quantity"));
                // Assuming you have a method to get Category by id
                service.setCategory_name(rs.getString("category_name"));
                service.setThumbnail(rs.getString("thumbnail"));
                service.setBrief_infor(rs.getString("brief_infor"));
                service.setService_detail(rs.getString("service_detail"));
                service.setImg_service(rs.getString("img_service"));
                service.setDate_add(rs.getString("date_add"));
                service.setService_Status(rs.getInt("service_Status"));
                service.setCreate_date(rs.getString("create_date"));

                services.add(service);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return services;
    }

    //BACHLNHE160284
    public void updateService(Service service) {
        String sql = "UPDATE service SET name_service = ?, original_prices = ?, sale_prices = ?, quantity = ?, "
                + "category_id = ?, thumbnail = ?, brief_infor = ?, service_detail = ?, img_service = ?, "
                + "date_add = GETDATE(), service_Status = ? WHERE service_id = ?";
        try (
                 PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, service.getName_service());
            ps.setFloat(2, service.getOriginal_prices());
            ps.setFloat(3, service.getSale_prices());
            ps.setInt(4, service.getQuantity());
            ps.setInt(5, service.getCategory().getCategory_id());
            ps.setString(6, service.getThumbnail());
            ps.setString(7, service.getBrief_infor());
            ps.setString(8, service.getService_detail());
            ps.setString(9, service.getImg_service());
            ps.setInt(10, service.getService_Status());
            ps.setInt(11, service.getService_id());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //BACHLNHE160284
    public Service getServiceById(int service_id) {
        Service service = null;
        String sql = "SELECT s.*, c.category_name FROM service s "
                + "JOIN category c ON s.category_id = c.category_id "
                + "WHERE s.service_id = ?";
        try (
                 PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, service_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setName_service(rs.getString("name_service"));
                service.setOriginal_prices(rs.getFloat("original_prices"));
                service.setSale_prices(rs.getFloat("sale_prices"));
                service.setQuantity(rs.getInt("quantity"));

                // Set Category with category_id and category_name
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                service.setCategory(category);

                service.setThumbnail(rs.getString("thumbnail"));
                service.setBrief_infor(rs.getString("brief_infor"));
                service.setService_detail(rs.getString("service_detail"));
                service.setImg_service(rs.getString("img_service"));
                service.setDate_add(rs.getString("date_add"));
                service.setService_Status(rs.getInt("service_Status"));
                service.setCreate_date(rs.getString("create_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return service;
    }

    //BACHLNHE160284
    public void deleteService(int serviceId) {
        String sql = "DELETE FROM service WHERE service_id = ?";
        try ( PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //BACHLNHE160284
    public int countSearch(String search) {
        String sql = "select count(*) from service where name_service like ?";
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
    public List<Service> searchServices(String searchTerm, String sortField, String sortOrder, int page, int pageSize) throws Exception {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT s.service_id, s.name_service, s.original_prices, s.sale_prices, s.quantity, s.thumbnail, s.brief_infor,\n"
                + "    s.service_detail, s.date_add, s.service_Status, s.create_date, s.img_service, c.category_id, c.category_name\n"
                + "FROM service s\n"
                + "INNER JOIN category c ON s.category_id = c.category_id\n"
                + "WHERE s.name_service LIKE ?\n"
                + "ORDER BY " + sortField + " " + sortOrder + "\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setService_id(rs.getInt("service_id"));
                    service.setCategory_id(rs.getInt("category_id"));
                    service.setName_service(rs.getString("name_service"));
                    service.setOriginal_prices(rs.getFloat("original_prices"));
                    service.setSale_prices(rs.getFloat("sale_prices"));
                    service.setQuantity(rs.getInt("quantity"));
                    // Assuming you have a method to get Category by id
                    service.setCategory_name(rs.getString("category_name"));
                    service.setThumbnail(rs.getString("thumbnail"));
                    service.setBrief_infor(rs.getString("brief_infor"));
                    service.setService_detail(rs.getString("service_detail"));
                    service.setImg_service(rs.getString("img_service"));
                    service.setDate_add(rs.getString("date_add"));
                    service.setService_Status(rs.getInt("service_Status"));
                    service.setCreate_date(rs.getString("create_date"));
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return services;
    }

    //BACHLNHE160284
    public int getTotalServiceCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM service";
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
    public List<Service> pagingService(int page, String sortField, String sortOrder) throws Exception {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT s.service_id, s.name_service, s.original_prices, s.sale_prices, s.quantity, s.thumbnail, s.brief_infor,\n"
                + "    s.service_detail, s.date_add, s.service_Status, s.create_date, s.img_service, c.category_id, c.category_name\n"
                + "FROM service s\n"
                + "INNER JOIN category c ON s.category_id = c.category_id\n"
                + "ORDER BY " + sortField + " " + sortOrder + "\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * 4);
            ps.setInt(2, 4);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setService_id(rs.getInt("service_id"));
                    service.setCategory_id(rs.getInt("category_id"));
                    service.setName_service(rs.getString("name_service"));
                    service.setOriginal_prices(rs.getFloat("original_prices"));
                    service.setSale_prices(rs.getFloat("sale_prices"));
                    service.setQuantity(rs.getInt("quantity"));
                    // Assuming you have a method to get Category by id
                    service.setCategory_name(rs.getString("category_name"));
                    service.setThumbnail(rs.getString("thumbnail"));
                    service.setBrief_infor(rs.getString("brief_infor"));
                    service.setService_detail(rs.getString("service_detail"));
                    service.setImg_service(rs.getString("img_service"));
                    service.setDate_add(rs.getString("date_add"));
                    service.setService_Status(rs.getInt("service_Status"));
                    service.setCreate_date(rs.getString("create_date"));
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public List<Service> getServices(int pageIndex, int pageSize) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT s.*, c.category_name FROM service s "
                + "JOIN category c ON s.category_id = c.category_id "
                + "ORDER BY s.date_add DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, (pageIndex - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setName_service(rs.getString("name_service"));
                service.setOriginal_prices(rs.getFloat("original_prices"));
                service.setSale_prices(rs.getFloat("sale_prices"));
                service.setQuantity(rs.getInt("quantity"));
                service.setThumbnail(rs.getString("thumbnail"));
                service.setBrief_infor(rs.getString("brief_infor"));
                service.setService_detail(rs.getString("service_detail"));
                service.setDate_add(rs.getString("date_add"));
                service.setService_Status(rs.getInt("service_Status"));
                service.setCreate_date(rs.getString("create_date"));
                service.setImg_service(rs.getString("img_service"));

                // Setting category information
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                service.setCategory(category);

                services.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }

    public int getTotalServices() {
        String query = "SELECT COUNT(*) AS total FROM service";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Service getServiceById1(int serviceId) {
        Service service = null;
        String query = "  SELECT s.service_id, s.category_id, s.name_service, s.original_prices, s.sale_prices, s.quantity,\n"
                + "                     s.thumbnail, s.brief_infor, s.service_detail, s.date_add, s.service_Status, s.create_date, \n"
                + "                     s.img_service, c.category_name \n"
                + "                     FROM service s \n"
                + "                     JOIN category c ON s.category_id = c.category_id \n"
                + "                     WHERE s.service_id = ?";

        try ( Connection connection = new DBContext().getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, serviceId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                service = new Service();
                service.setService_id(resultSet.getInt("service_id"));
                service.setCategory_id(resultSet.getInt("category_id"));
                service.setName_service(resultSet.getString("name_service"));
                service.setOriginal_prices(resultSet.getFloat("original_prices"));
                service.setSale_prices(resultSet.getFloat("sale_prices"));
                service.setQuantity(resultSet.getInt("quantity"));
                service.setCategory_name(resultSet.getString("category_name"));
                service.setThumbnail(resultSet.getString("thumbnail"));
                service.setBrief_infor(resultSet.getString("brief_infor"));
                service.setService_detail(resultSet.getString("service_detail"));
                service.setDate_add(resultSet.getString("date_add"));
                service.setService_Status(resultSet.getInt("service_Status"));
                service.setCreate_date(resultSet.getString("create_date"));
                service.setImg_service(resultSet.getString("img_service"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    public List<String> getimg(String img) {
        String[] imgl = img.split(",");
        List<String> imgl1 = new ArrayList<String>();
        for (int i = 0; i < imgl.length; i++) {
            imgl1.add(imgl[i].trim());
        }
        return imgl1;
    }

//    public List<Service> getServicesByPage(int pageIndex, int pageSize) {
//        List<Service> list = new ArrayList<>();
//        String sql = "SELECT * FROM service ORDER BY date_add DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
//        try {
//            Connection con = new DBContext().getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, (pageIndex - 1) * pageSize);
//            ps.setInt(2, pageSize);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Service service = new Service();
//                // set service properties
//                service.setService_id(rs.getInt("service_id"));
//                service.setName_service(rs.getString("name_service"));
//                service.setOriginal_prices(rs.getFloat("original_prices"));
//                service.setSale_prices(rs.getFloat("sale_prices"));
//                service.setThumbnail(rs.getString("thumbnail"));
//                service.setBrief_infor(rs.getString("brief_infor"));
//                service.setDate_add(rs.getString("date_add"));
//                service.setCategory_id(rs.getInt("category_id"));
//                // add service to list
//                list.add(service);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public int countTotalServices() {
//        String sql = "SELECT COUNT(*) FROM service";
//        try {
//            Connection con = new DBContext().getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
    public List<Service> getAllServicesByCate(int categoryId) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM service WHERE category_id = ?";

        try ( Connection connection = new DBContext().getConnection();  PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Service service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setCategory_id(rs.getInt("category_id"));
                service.setName_service(rs.getString("name_service"));
                service.setOriginal_prices(rs.getFloat("original_prices"));
                service.setSale_prices(rs.getFloat("sale_prices"));
                service.setQuantity(rs.getInt("quantity"));
                service.setCategory_name(rs.getString("category_name"));
                service.setThumbnail(rs.getString("thumbnail"));
                service.setBrief_infor(rs.getString("brief_infor"));
                service.setService_detail(rs.getString("service_detail"));
                service.setDate_add(rs.getString("date_add"));
                service.setService_Status(rs.getInt("service_Status"));
                service.setCreate_date(rs.getString("create_date"));
                service.setImg_service(rs.getString("img_service"));
                services.add(service);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }

    public List<Service> getServicesByCategory(int categoryId, int pageIndex, int pageSize) {
        List<Service> services = new ArrayList<>();
        try {
            String query = "SELECT * FROM Service WHERE category_id = ? ";
            Connection connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, (pageIndex - 1) * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                // Populate service object with data from result set
                service.setService_id(rs.getInt("service_id"));
                service.setCategory_id(rs.getInt("category_id"));
                service.setName_service(rs.getString("name_service"));
                service.setOriginal_prices(rs.getFloat("original_prices"));
                service.setSale_prices(rs.getFloat("sale_prices"));
                service.setQuantity(rs.getInt("quantity"));
                service.setCategory_name(rs.getString("category_name"));
                service.setThumbnail(rs.getString("thumbnail"));
                service.setBrief_infor(rs.getString("brief_infor"));
                service.setService_detail(rs.getString("service_detail"));
                service.setDate_add(rs.getString("date_add"));
                service.setService_Status(rs.getInt("service_Status"));
                service.setCreate_date(rs.getString("create_date"));
                service.setImg_service(rs.getString("img_service"));
                services.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }
//    public static void main(String[] args) {
//        ServiceDAO serviceDAO = new ServiceDAO();
//
//        // Test parameters
//        int categoryId = 1; // Replace with a valid category ID from your database
//        int pageIndex = 1; // Starting from page 1
//        int pageSize = 2; // Number of services per page
//
//        // Call the method and get the list of services
//        List<Service> services = serviceDAO.getServicesByCategory(categoryId, pageIndex, pageSize);
//
//        // Print the services to verify the results
//        for (Service service : services) {
//            System.out.println(service);
//        }
//    }

    public List<Service> getServiceNew() {
        List<Service> list = new ArrayList<>();
        CategoryDAO d = new CategoryDAO();
        try {
            String sql = "SELECT TOP 3 *\n"
                    + "FROM service\n"
                    + "WHERE service_Status = 1\n"
                    + "ORDER BY date_add DESC, create_date DESC; ";
            Connection connection = new DBContext().getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Service pr = new Service();
                //==================================
                pr.setService_id(rs.getInt(1));
                pr.setName_service(rs.getString(2));
                pr.setOriginal_prices(rs.getFloat(3));
                pr.setSale_prices(rs.getFloat(4));
                pr.setThumbnail(rs.getString(7));
                pr.setBrief_infor(rs.getString(8));
                list.add(pr);
            }
        } catch (Exception ex) {

        }

        return list;
    }

    public static void main(String[] args) {
        // Create an instance of ServiceDAO
        ServiceDAO serviceDAO = new ServiceDAO();

        // Call the getServiceNew method to retrieve the list of services
        List<Service> services = serviceDAO.getServiceNew();

        // Print out the services to verify the output
        for (Service service : services) {
            System.out.println(service.toString());
        }
    }
}
