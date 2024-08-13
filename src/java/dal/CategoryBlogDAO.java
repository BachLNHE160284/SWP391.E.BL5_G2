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
import model.Category_Blog;

/**
 *
 * @author ntung
 */
public class CategoryBlogDAO extends DBContext {
    public List<Category_Blog> getAllCategoryBlog(){
        List<Category_Blog> lctb = new ArrayList<>();
        try{
        Connection con = new DBContext().getConnection();
        String sql = "select * from category_blog ";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category_Blog cb = new Category_Blog();
                cb.setCategory_id(rs.getInt(1));
                cb.setCategory_name(rs.getString(2));
                lctb.add(cb);
            }   
        } catch (Exception ex) {
            
        }
        return lctb;
    }
    public String GetNameCategoryBlogByID(int id) {
        
        List<Category_Blog> list = new ArrayList<>();
        try {
            Connection con = new DBContext().getConnection();
            String sql = "select*from category_blog";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Category_Blog category1 = new Category_Blog();
                category1.setCategory_id(rs.getInt(1));
                category1.setCategory_name(rs.getString(2));
                list.add(category1);
            }
        } catch (Exception ex) {
        }
        for (Category_Blog o : list) {
            if (o.getCategory_id() == id) {
                return o.getCategory_name();
            }
        }
        return null;
    }
//    
//    public static void main(String[] args) {
//        CategoryBlogDAO ct = new CategoryBlogDAO();         
//        System.out.println(ct.getAllCategoryBlog());
//    }
}
