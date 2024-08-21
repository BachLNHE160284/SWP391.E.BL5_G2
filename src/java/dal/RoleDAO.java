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
import model.Role;

/**
 *
 * @author Admin
 */
public class RoleDAO extends DBContext{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Role> getAll(){
        List<Role> roles = new ArrayList<>();
        try {
            String query = "SELECT * FROM [dbo].[role]";
            ResultSet rs = null;
            PreparedStatement ps = null;
            Connection conn = null;
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Role role = new Role();
                role.setRole_id(rs.getInt("role_id"));
                role.setRole_name(rs.getString("role_name"));
                roles.add(role);
            }
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
    
    public String getRoleById(int id) {

        try {
            Connection con = new DBContext().getConnection();
            String sql = "select role_name from role where role_id=" + id + ";";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String role = rs.getString(1);
                return role;
            }
        } catch (Exception ex) {
        }
        return null;
    }
//    public static void main(String[] args) {
//        // Tạo một đối tượng RoleDao
//        RoleDAO roleDao = new RoleDAO();
//        
//        // Kiểm tra hàm getRoleById với một id cụ thể
//        int testRoleId = 4; // Thay giá trị này bằng ID bạn muốn kiểm tra
//        String roleName = roleDao.getRoleById(testRoleId);
//        
//        // In ra kết quả
//        if (roleName != null) {
//            System.out.println("Role ID: " + testRoleId + " corresponds to Role Name: " + roleName);
//        } else {
//            System.out.println("Role ID: " + testRoleId + " does not exist.");
//        }
//    }

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        try {
            Connection con = new DBContext().getConnection();
            String sql = "SELECT * FROM role";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(new Role(rs.getInt(1), rs.getString(2)));
            }
            return list;
        } catch (Exception ex) {
        }
        return null;
    }
//    public static void main(String[] args) {
//        // Tạo một đối tượng RoleDao
//        RoleDAO roleDao = new RoleDAO();
//        
//        // Gọi hàm getAllRole để lấy danh sách tất cả các role
//        List<Role> roles = roleDao.getAllRole();
//        
//        // Kiểm tra xem danh sách roles có null hay không và in ra kết quả
//        if (roles != null) {
//            System.out.println("List of roles:");
//            for (Role role : roles) {
//                System.out.println("Role ID: " + role.getRole_id()+ ", Role Name: " + role.getRole_name());
//            }
//        } else {
//            System.out.println("No roles found or an error occurred.");
//        }
//    }

    public int getNumberRole(String url) {
        int count = 0;
        try {
            Connection con = new DBContext().getConnection();
            String sql = "select * from userauthorization where URL like '" + url + "'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                count = count + 1;
            }
        } catch (Exception e) {
        }
        return count;
    }

    public boolean Haspermission(int role, String url) {
        url = url.substring(1);
        System.out.println(url);
        try {
            Connection con = new DBContext().getConnection();
            String sql = "select * from userauthorization where URL like '" + url + "'";
            int number = getNumberRole(url);
            Statement stm = con.createStatement();
            if (number == 0) {
                return true;
            }
            ResultSet rs = stm.executeQuery(sql);
            if (number == 1) {
                while (rs.next()) {
                    int rOle = rs.getInt(1);
                    if (rOle == role) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                while (rs.next()) {
                    int rOle = rs.getInt(1);
                    System.out.println(rOle);
                    if (rOle == role) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public static void main(String[] args) {
//        RoleDAO rd = new RoleDAO();
//        int role = 5;
//        String url = "/ServiceList";
//        System.out.println(rd.Haspermission(role, url));
//    }
    
}
