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
}
