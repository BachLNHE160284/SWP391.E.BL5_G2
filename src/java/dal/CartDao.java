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

    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE user_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setUser_id(rs.getInt("user_id"));

                    Service service = new Service(); // Create a new Service object
                    service.setService_id(rs.getInt("service_id")); // Set the service_id from the ResultSet
                    cart.setService(service); // Set the Service object in the Cart

                    cart.setQuantity(rs.getInt("quantity"));
                    cartList.add(cart);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cartList;
    }

    public void Addtocart(int userId, int serviceID, int quantity) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();

            String checkQuery = "SELECT quantity FROM Cart WHERE user_id = ? AND service_id = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setInt(1, userId);
            stmt.setInt(2, serviceID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // If the item exists, update the quantity
                int currentQuantity = rs.getInt("quantity");
                String updateQuery = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND service_id = ?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setInt(1, currentQuantity + quantity);
                stmt.setInt(2, userId);
                stmt.setInt(3, serviceID);
            } else {
                // If the item does not exist, insert a new row
                String insertQuery = "INSERT INTO Cart (user_id, service_id, quantity) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);
                stmt.setInt(1, userId);
                stmt.setInt(2, serviceID);
                stmt.setInt(3, quantity);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void Addtocart(int user_id, int service_ID) {
        boolean check = false;
        int quantity = 0;
        List<Cart> l = getCartByUserId(user_id);
        for (Cart c : l) {
            if (c.getService().getService_id() == service_ID) {
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

    public void updateCartItem(int userId, int serviceID, int quantity) throws Exception {
        String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND service_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, serviceID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItem(int userId, int serviceID) throws Exception {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND service_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, serviceID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Step 1: Create an instance of CartDao
        CartDao cartDao = new CartDao();

        // Step 2: Call the getCartByUserId method with user_id = 8
        List<Cart> cartList = cartDao.getCartByUserId(8);

        // Step 3: Print the cart details to verify the output
        for (Cart cart : cartList) {
            System.out.println("User ID: " + cart.getUser_id());
            System.out.println("Service ID: " + cart.getService().getService_id());
            System.out.println("Quantity: " + cart.getQuantity());
            System.out.println("-------------------------------------------");
        }
    }

}
