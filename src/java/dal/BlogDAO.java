/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import model.Blog;
import java.sql.*;
import model.Category_Blog;

/**
 *
 * @author ntung
 */
public class BlogDAO {

    public void addBlog(Blog blog) {
    try {
        Connection con = new DBContext().getConnection();
        String sql = "INSERT INTO blog (title, content, author_id, updated_by, thumbnail, brief_infor, category_id, create_date, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, blog.getTittle());
        stmt.setString(2, blog.getContent());
        stmt.setInt(3, blog.getAuthor_id());
        stmt.setInt(4, blog.getUpdate_by());
        stmt.setString(5, blog.getThumbnail());
        stmt.setString(6, blog.getBrief_infor());
        stmt.setInt(7, blog.getCategory_id());
        stmt.setInt(8, blog.getStatus());  // Assuming status is an integer in your database
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


//    public static void main(String[] args) {
//    BlogDAO dao = new BlogDAO();
//
//    // Create a Blog object and set its properties
//    Blog blog = new Blog();
//    blog.setTittle("Sample Blog Title");             // Title of the blog
//    blog.setContent("This is the content of the blog."); // Content of the blog
//    blog.setAuthor_id(1);                            // Author ID (Ensure this ID exists in the user table)
//    blog.setUpdate_by(1);                           // ID of the user who updates the blog (Ensure this ID exists in the user table)
//    blog.setThumbnail("thumbnail.jpg");             // Thumbnail image file name
//    blog.setBrief_infor("This is a brief info.");     // Brief information about the blog
//    blog.setCategory_id(1);                          // Category ID (Ensure this ID exists in the category_blog table)
//    blog.setStatus(1);                              // Status of the blog (1 for active, 0 for inactive)
//
//    // Add the blog using the DAO
//    dao.addBlog(blog);
//    System.out.println("Blog added successfully.");
//}

}
