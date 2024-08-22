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
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
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
        List<Cart> l = getCartByUserId(user_id);
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
