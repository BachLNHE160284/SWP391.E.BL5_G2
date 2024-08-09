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
import model.User;

/**
 *
 * @author MinhHieu
 */
public class UserDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User login(String email, String password) {
        String query = "SELECT * FROM [SWP391BL5G2_4].[dbo].[user] WHERE [email_address] = ? AND [password] = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("phone_number"),
                        rs.getString("email_address"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("avartar"),
                        rs.getInt("role_id"),
                        rs.getInt("Status"),
                        rs.getString("create_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            }
        }
        return null; // Return null if login fails or an exception occurred
    }

    public void registerCustomer(String fullname, boolean gender, String emailAddress, String password) {
        String query = "INSERT INTO [SWP391BL5G2_4].[dbo].[user] "
                + "([fullname], [gender], [email_address], [password], [role_id], [Status], [create_date]) "
                + "VALUES (?, ?, ?, ?, 3, 1, GETDATE());";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setBoolean(2, gender);
            ps.setString(3, emailAddress);
            ps.setString(4, password);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public User checkRegister(String emailAddress) {
        String query = "SELECT * FROM [SWP391BL5G2_4].[dbo].[user] WHERE [email_address] = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("phone_number"),
                        rs.getString("email_address"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("avartar"),
                        rs.getInt("role_id"),
                        rs.getInt("Status"),
                        rs.getString("create_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean updateByEmail(String email, String newPassword) throws Exception {
        String query = "UPDATE [SWP391BL5G2_4].[dbo].[user] SET [password] = ? WHERE [email_address] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, email);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public void changePassword(String email, String newPassword) {
        String query = "UPDATE User Set password = ? where email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        UserDAO dAO = new UserDAO();
        String fullname = "Thang";
        boolean gender = true;
        String emailAddress = "thang@example.com";
        String password = "password123";
        dAO.registerCustomer(fullname, gender, emailAddress, password);

    }

}
