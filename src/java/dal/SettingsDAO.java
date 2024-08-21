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
import model.Role;
import model.Settings;

/**
 *
 * @author Admin
 */
public class SettingsDAO extends DBContext{
    public List<Settings> getAll(){
        String sql = "SELECT DISTINCT auth.URL FROM userauthorization auth";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        List<Settings> settings = new ArrayList<>();
        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Settings st = new Settings();
                st.setUrl(rs.getString("url"));
                settings.add(st);
            }
        }catch(Exception ex){
            
        }
        return settings;
    }
    
    public Settings getDetail(String url){
        String sql = "SELECT auth.* , r.role_name FROM userauthorization auth left join [role] r on auth.role_id = r.role_id WHERE auth.[url] = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, url);
            rs = ps.executeQuery();
            Settings st = new Settings();
            st.setUrl(url);
            List<Role> roles = new ArrayList<>();
            while(rs.next()){
                st.setUrl(rs.getString("url"));
                Role role = new Role();
                role.setRole_id(rs.getInt("role_id"));
                role.setRole_name(rs.getString("role_name"));
                roles.add(role);
            }
            st.setRoles(roles);
            return st;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
