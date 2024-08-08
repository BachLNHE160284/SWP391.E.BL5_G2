/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import model.Blog;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public List<Blog> getAllBlogNewtop6() {
        List<Blog> list = new ArrayList<>();
        try {
            Connection con = new DBContext().getConnection();
            String sql = "SELECT TOP 6 * \n"
                    + "FROM blog \n"
                    + "WHERE status = 1 \n"
                    + "ORDER BY update_date DESC, create_date DESC;";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog bl = new Blog();
                bl.setBlog_id(rs.getInt(1));
                bl.setTittle(rs.getString(2));
                bl.setContent(rs.getString(3));
                bl.setAuthor_id(rs.getInt(4));
                bl.setThumbnail(rs.getString(7));
                bl.setBrief_infor(rs.getString(8));
                list.add(bl);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public Blog getBlogNew() {
        List<Blog> list = getAllBlogNewtop6();
        return list.get(0);
    }

//    public static void main(String[] args) {
//        BlogDAO bd = new BlogDAO();
//        System.out.println(bd.getAllBlogNewtop6().size());
//
//    }
//    public static void main(String[] args) {
//    BlogDAO dao = new BlogDAO();
//
//    // Create a Blog object and set its properties
//    Blog blog = new Blog();
//    blog.setTittle("Sample Blog Title 2");             // Title of the blog
//    blog.setContent("This is the content of the blog 2."); // Content of the blog
//    blog.setAuthor_id(1);                            // Author ID (Ensure this ID exists in the user table)
//    blog.setUpdate_by(1);                           // ID of the user who updates the blog (Ensure this ID exists in the user table)
//    blog.setThumbnail("thumbnail1.jpg");             // Thumbnail image file name
//    blog.setBrief_infor("This is a brief info 1.");     // Brief information about the blog
//    blog.setCategory_id(1);                          // Category ID (Ensure this ID exists in the category_blog table)
//    blog.setStatus(1);                              // Status of the blog (1 for active, 0 for inactive)
//
//    // Add the blog using the DAO
//    dao.addBlog(blog);
//    System.out.println("Blog added successfully.");
//}
    
    
    public List<Blog> getPaginatedBlogs(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog ORDER BY update_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        
        try ( Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlog_id(rs.getInt("blog_id"));
                blog.setTittle(rs.getString("title"));
                blog.setBrief_infor(rs.getString("brief_infor"));
                blog.setThumbnail(rs.getString("thumbnail"));
                blog.setUpdate_date(rs.getDate("update_date"));
                blogs.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return blogs;
    }

    public int getTotalBlogs() {
        String sql = "SELECT COUNT(*) FROM blog";
        try (Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
        public static void main(String[] args) {
        BlogDAO blogDAO = new BlogDAO();

        // Set pagination parameters
        int page = 1; // Get the first page
        int pageSize = 5; // Number of blogs per page

        // Get the paginated blogs
        List<Blog> blogs = blogDAO.getPaginatedBlogs(page, pageSize);

        // Print out the blogs
        for (Blog blog : blogs) {
            System.out.println(blog.toString());
        }
    }
    
}
