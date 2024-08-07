/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import context.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author lebac
 */
public class CategoryDAO extends DBContext{
    public List<Category> getAllCategories() throws Exception {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select * from category";
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategory_id(rs.getInt("category_id"));
                cate.setCategory_name(rs.getString("category_name"));
                list.add(cate);
            }
        } catch (SQLException | ClassNotFoundException ex) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        CategoryDAO cate = new CategoryDAO();
        try {
            List<Category> categories = cate.getAllCategories();
            for (Category category : categories) {
                System.out.println(category.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
