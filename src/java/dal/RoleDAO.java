/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
