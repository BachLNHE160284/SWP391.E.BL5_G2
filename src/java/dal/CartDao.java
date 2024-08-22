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
import model.Cart;
import model.Service;

/**
 *
 * @author ntung
 */
public class CartDao {

    public boolean deleteCart(int uid, int serviceID) {
        try {
            Connection con = new DBContext().getConnection();
            String sql = "delete from cart where user_id = " + uid + " and service_id = " + serviceID + "";
            Statement stm = con.createStatement();
            stm.executeUpdate(sql);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }

    public List<Cart> getALlCartByUserID(int id) {
        ServiceDAO s = new ServiceDAO();
        List<Cart> list = new ArrayList<>();
        try {
            Connection con = new DBContext().getConnection();
            String sql = "select * from cart where user_id = " + id + ";";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setUser_id(rs.getInt(1));
                cart.setService(s.getServiceById(rs.getInt(2)));
                cart.setQuantity(rs.getInt(3));
                list.add(cart);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public void Addtocart(int user_id, int service_ID, int quantity) {
        try {
            Connection con = new DBContext().getConnection();
            String sql = "insert into cart values"
                    + "(" + user_id + ", " + service_ID + ", " + quantity + ");";
            System.out.println(sql);
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void Addtocart(int user_id, int service_ID) {
        boolean check = false;
        int quantity = 0;
        List<Cart> l = getALlCartByUserID(user_id);
        for (Cart c : l) {
            if (c.getService().getService_id()== service_ID) {
                quantity = c.getQuantity();
                check = true;
            }
        }
        if (check == false) {
            try {
                Connection con = new DBContext().getConnection();
                String sql = "insert into cart values"
                        + "(" + user_id + ", " + service_ID + ", " + 1 + ");";
                System.out.println(sql);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.executeUpdate();
            } catch (Exception e) {
            }
        } else {
            try {
                Connection con = new DBContext().getConnection();
                String sql = "update cart set quantity = " + (quantity + 1) + " where service_id = " + service_ID + " and user_id = " + user_id + ";";
                System.out.println(sql);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.executeUpdate();
            } catch (Exception e) {
            }
        }
    }

    public double getTotals(List<Cart> lc) {
        double totals = 0;
        for (Cart c : lc) {
            totals = totals + c.getQuantity() * c.getService().getSale_prices();
        }
        return totals;
    }

    public void UpdateCart(boolean check, String uservice, String servicedelete, int user_id) {
        if (servicedelete != null) {
            try {
                Connection con = new DBContext().getConnection();
                String sql = "delete from cart where user_id = " + user_id + " and  service_id = " + servicedelete + ";";
                System.out.println(sql);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.executeUpdate();
            } catch (Exception e) {

            }
        }
        if (uservice != null) {
            try {
                Connection con = new DBContext().getConnection();
                int quantity = 0;
                String sql1 = "select quantity from cart where service_id = " + uservice + " and user_id = " + user_id + ";";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql1);
                while (rs.next()) {
                    quantity = rs.getInt(1);
                }
                if (check == true) {
                    quantity = quantity + 1;

                    String sql = " update cart set quantity = " + quantity + " where service_id = " + uservice + " and user_id = " + user_id + ";";
                    System.out.println(sql);
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.executeUpdate();
                } else {
                    quantity = quantity - 1;
                    if (quantity < 0) {
                        quantity = quantity + 1;
                    }
                    String sql = " update cart set quantity = " + quantity + " where service_id = " + uservice + " and user_id = " + user_id + ";";
                    System.out.println(sql);
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.executeUpdate();
                }
            } catch (Exception e) {

            }
        }
    }
    
//    public Cart getCartByUserIdAndServiceId(int userId, int serviceId) {
//        String query = "SELECT * FROM Cart WHERE user_id = ? AND service_id = ?";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setInt(1, userId);
//            ps.setInt(2, serviceId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                ServiceDAO serviceDAO = new ServiceDAO();
//                Service service = serviceDAO.getServiceById(serviceId);
//                int quantity = rs.getInt("quantity");
//                return new Cart(userId, service, quantity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void addToCart(int userId, Service service, int quantity) {
//        Cart existingCart = getCartByUserIdAndServiceId(userId, service.getService_id());
//        if (existingCart != null) {
//            // Update existing cart
//            updateCartQuantity(userId, service.getService_id(), existingCart.getQuantity() + quantity);
//        } else {
//            // Insert new cart
//            insertNewCart(userId, service, quantity);
//        }
//    }
//
//    private void updateCartQuantity(int userId, int serviceId, int newQuantity) {
//        String query = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND service_id = ?";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setInt(1, newQuantity);
//            ps.setInt(2, userId);
//            ps.setInt(3, serviceId);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void insertNewCart(int userId, Service service, int quantity) {
//        String query = "INSERT INTO Cart (user_id, service_id, quantity) VALUES (?, ?, ?)";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setInt(1, userId);
//            ps.setInt(2, service.getService_id());
//            ps.setInt(3, quantity);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public List<Cart> getCartByUserId(int id) {
        ServiceDAO serviceDAO = new ServiceDAO();
        List<Cart> list = new ArrayList<>();
        String sql = "Select * from cart where user_id = ?";
        try {Connection conn = new DBContext().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart p = new Cart(
                        rs.getInt("userID"),
                        serviceDAO.getServiceById(rs.getInt("ServiceId")),
                        rs.getInt("quantity"));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public void AddCart(Cart cart) {
        String sql = "insert into cart (user_id, service_id, quantity) values (?, ?, ?);";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUser_id());
            ps.setInt(2, cart.getService().getService_id());
            ps.setInt(3, cart.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void UpdateCart(int userId, int quantity, int serviceID) {
        String sql = "update cart set quantity = ?  where user_id = ? and service_id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2, userId);
            ps.setInt(1, quantity);
            ps.setInt(3, serviceID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
