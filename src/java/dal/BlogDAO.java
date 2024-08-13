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
//        public static void main(String[] args) {
//        BlogDAO blogDAO = new BlogDAO();
//
//        // Set pagination parameters
//        int page = 1; // Get the first page
//        int pageSize = 5; // Number of blogs per page
//
//        // Get the paginated blogs
//        List<Blog> blogs = blogDAO.getPaginatedBlogs(page, pageSize);
//
//        // Print out the blogs
//        for (Blog blog : blogs) {
//            System.out.println(blog.toString());
//        }
//    }
    public Blog getBlogById(int blogId) {
        CategoryBlogDAO d = new CategoryBlogDAO();

        try {
            Connection con = new DBContext().getConnection();
            String query = "SELECT * FROM blog WHERE blog_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, blogId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlog_id(rs.getInt(1));
                blog.setTittle(rs.getString(2));
                blog.setContent(rs.getString(3));
                blog.setAuthor_id(rs.getInt(4));
                blog.setUpdate_by(rs.getInt(5));
                blog.setUpdate_date(rs.getDate(6));
                blog.setThumbnail(rs.getString(7));
                int status = rs.getInt(11);
                blog.setBrief_infor(rs.getString(8));
                blog.setCategory_name(d.GetNameCategoryBlogByID(rs.getInt(9)));
                blog.setCreate_date(rs.getDate(10));
                //blog.setNameAuthor(ud.getNameUserById(blog.getAuthor_id()));
                return blog;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    
    public int getQuantityByCateId(int category_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "  select COUNT(*) from [blog]\n" +
"                    where Category_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return 0;
    }
//    public static void main(String[] args) {
//        // Create an instance of BlogDAO
//        BlogDAO blogDAO = new BlogDAO();
//
//        // Test with a sample category_id
//        int categoryId = 1; // Replace with an actual category_id present in your database
//        int quantity = blogDAO.getQuantityByCateId(categoryId);
//
//        // Print the result
//        System.out.println("The number of blogs in category " + categoryId + " is: " + quantity);
//    }
    
    public List<Blog> getAllBlogsByCateId(int cid) {
        List<Blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from blog where category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("blog_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("updated_by"),
                        rs.getDate("update_date"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_infor"),
                        rs.getInt("category_id"),
                        rs.getDate("create_date"),
                        rs.getInt("status")
                    );
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
//     public static void main(String[] args) {
//        // Create an instance of BlogDAO
//        BlogDAO blogDAO = new BlogDAO();
//        
//        // Define a sample category_id for testing
//        int categoryId = 1; // Replace with an actual category_id present in your database
//        
//        // Get all blogs for the specified category_id
//        List<Blog> blogs = blogDAO.getAllBlogsByCateId(categoryId);
//        
//        // Print the details of each blog
//        if (blogs != null && !blogs.isEmpty()) {
//            for (Blog blog : blogs) {
//                System.out.println("Title: " + blog.getTittle());
//                System.out.println("Content: " + blog.getContent());
//                System.out.println("Author ID: " + blog.getAuthor_id());
//                System.out.println("Updated By: " + blog.getUpdate_by());
//                System.out.println("Update Date: " + blog.getUpdate_date());
//                System.out.println("Thumbnail: " + blog.getThumbnail());
//                System.out.println("Brief Info: " + blog.getBrief_infor());
//                System.out.println("Category Name: " + blog.getCategory_name());
//                System.out.println("Create Date: " + blog.getCreate_date());
//                System.out.println("Status: " + blog.getStatus());
//                System.out.println("---------");
//            }
//        } else {
//            System.out.println("No blogs found for category ID " + categoryId);
//        }
//    }
    public List<Blog> getAllBlogs() {
        List<Blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from blog";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("blog_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("updated_by"),
                        rs.getDate("update_date"),
                        rs.getString("thumbnail"),
                        rs.getString("brief_infor"),
                        rs.getInt("category_id"),
                        rs.getDate("create_date"),
                        rs.getInt("status")
                    );
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        // Create an instance of BlogDAO
        BlogDAO blogDAO = new BlogDAO();
        
        // Get all blogs from the database
        List<Blog> blogs = blogDAO.getAllBlogs();
        
        // Print the details of each blog
        if (blogs != null && !blogs.isEmpty()) {
            for (Blog blog : blogs) {
                System.out.println("Blog ID: " + blog.getBlog_id());
                System.out.println("Title: " + blog.getTittle());
                System.out.println("Content: " + blog.getContent());
                System.out.println("Author ID: " + blog.getAuthor_id());
                System.out.println("Updated By: " + blog.getUpdate_by());
                System.out.println("Update Date: " + blog.getUpdate_date());
                System.out.println("Thumbnail: " + blog.getThumbnail());
                System.out.println("Brief Info: " + blog.getBrief_infor());
                System.out.println("Category ID: " + blog.getCategory_id());
                System.out.println("Create Date: " + blog.getCreate_date());
                System.out.println("Status: " + blog.getStatus());
                System.out.println("---------");
            }
        } else {
            System.out.println("No blogs found.");
        }
    }
    
}