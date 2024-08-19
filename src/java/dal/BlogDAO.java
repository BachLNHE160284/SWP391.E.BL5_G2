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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category_Blog;

/**
 *
 * @author ntung
 */
public class BlogDAO extends DBContext {

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

    public List<Blog> getPaginatedBlogs(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blog ORDER BY update_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( Connection con = new DBContext().getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
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
        try ( Connection con = new DBContext().getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

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
            String sql = "  select COUNT(*) from [blog]\n"
                    + "                    where Category_ID = ?";
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

    //BACHLNHE160284
    public List<Blog> getAllBlog() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.title, b.content, b.author_id, b.updated_by, b.update_date, b.thumbnail, "
                + "b.brief_infor, b.category_id, b.create_date, b.status, c.category_name "
                + "FROM blog b "
                + "INNER JOIN category_blog c ON b.category_id = c.Category_ID;";

        try (
                 Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlog_id(rs.getInt("blog_id"));
                blog.setTittle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setAuthor_id(rs.getInt("author_id"));
                blog.setUpdate_by(rs.getInt("updated_by"));
                blog.setUpdate_date(rs.getDate("update_date"));
                blog.setThumbnail(rs.getString("thumbnail"));
                blog.setBrief_infor(rs.getString("brief_infor"));
                blog.setCategory_id(rs.getInt("category_id"));
                blog.setCreate_date(rs.getDate("create_date"));
                blog.setStatus(rs.getInt("status"));
                blog.setCategory_name(rs.getString("category_name")); // Thiết lập tên chuyên mục

                blogs.add(blog);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return blogs;
    }

    //BACHLNHE160284
    public Blog getBlogByID(int blog_id) {
        Blog blog = null;
        String sql = "SELECT b.blog_id, b.title, b.content, b.author_id, b.updated_by, b.update_date, b.thumbnail, "
                + "b.brief_infor, b.category_id, b.create_date, b.status, c.category_name "
                + "FROM blog b "
                + "INNER JOIN category_blog c ON b.category_id = c.Category_ID "
                + "WHERE b.blog_id = ?;";

        try (
                 Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, blog_id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    blog = new Blog();
                    blog.setBlog_id(rs.getInt("blog_id"));
                    blog.setTittle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blog.setAuthor_id(rs.getInt("author_id"));
                    blog.setUpdate_by(rs.getInt("updated_by"));
                    blog.setUpdate_date(rs.getDate("update_date"));
                    blog.setThumbnail(rs.getString("thumbnail"));
                    blog.setBrief_infor(rs.getString("brief_infor"));
                    blog.setCategory_id(rs.getInt("category_id"));
                    blog.setCreate_date(rs.getDate("create_date"));
                    blog.setStatus(rs.getInt("status"));
                    blog.setCategory_name(rs.getString("category_name"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return blog;
    }

    //BACHLNHE160284
    public int countSearch(String search) {
        String sql = "select count(*) from blog where title like ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //BACHLNHE160284
    public List<Blog> searchBlog(String keyword, String sortField, String sortOrder, int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.title, b.content, b.author_id, b.updated_by, b.update_date, b.thumbnail, "
                + "b.brief_infor, b.category_id, b.create_date, b.status, c.category_name "
                + "FROM blog b "
                + "INNER JOIN category_blog c ON b.category_id = c.category_id "
                + "WHERE b.title LIKE ? "
                + "ORDER BY " + sortField + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try (
                 Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set parameters
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlog_id(rs.getInt("blog_id"));
                    blog.setTittle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blog.setAuthor_id(rs.getInt("author_id"));
                    blog.setUpdate_by(rs.getInt("updated_by"));
                    blog.setUpdate_date(rs.getDate("update_date"));
                    blog.setThumbnail(rs.getString("thumbnail"));
                    blog.setBrief_infor(rs.getString("brief_infor"));
                    blog.setCategory_id(rs.getInt("category_id"));
                    blog.setCreate_date(rs.getDate("create_date"));
                    blog.setStatus(rs.getInt("status"));
                    blog.setCategory_name(rs.getString("category_name"));

                    blogs.add(blog);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    //BACHLNHE160284
    public int getTotalBlogCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM blog";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //BACHLNHE160284
    public List<Blog> pagingBlog(int page, String sortField, String sortOrder) throws Exception {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.title, b.content, b.author_id, b.updated_by, b.update_date, b.thumbnail, "
                + "b.brief_infor, b.category_id, b.create_date, b.status, c.category_name "
                + "FROM blog b "
                + "INNER JOIN category_blog c ON b.category_id = c.Category_ID "
                + "ORDER BY " + sortField + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * 4);
            ps.setInt(2, 4);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlog_id(rs.getInt("blog_id"));
                    blog.setTittle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blog.setAuthor_id(rs.getInt("author_id"));
                    blog.setUpdate_by(rs.getInt("updated_by"));
                    blog.setUpdate_date(rs.getDate("update_date"));
                    blog.setThumbnail(rs.getString("thumbnail"));
                    blog.setBrief_infor(rs.getString("brief_infor"));
                    blog.setCategory_id(rs.getInt("category_id"));
                    blog.setCreate_date(rs.getDate("create_date"));
                    blog.setStatus(rs.getInt("status"));
                    blog.setCategory_name(rs.getString("category_name"));
                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs;
    }
    
    //BACHLNHE160284
    public void deleteBlog(int blogId) {
        String sql = "DELETE FROM blog WHERE blog_id = ?";
        try ( PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //BACHLNHE160284
    public void updateBlog(Blog blog) {
    try {
        Connection con = new DBContext().getConnection();
        String sql = "UPDATE blog SET title = ?, content = ?, author_id = ?, updated_by = ?, thumbnail = ?, "
                   + "brief_infor = ?, category_id = ?, update_date = GETDATE(), status = ? WHERE blog_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, blog.getTittle());
        stmt.setString(2, blog.getContent());
        stmt.setInt(3, blog.getAuthor_id());
        stmt.setInt(4, blog.getUpdate_by());
        stmt.setString(5, blog.getThumbnail());
        stmt.setString(6, blog.getBrief_infor());
        stmt.setInt(7, blog.getCategory_id());
        stmt.setInt(8, blog.getStatus()); 
        stmt.setInt(9, blog.getBlog_id()); 
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        // Tạo đối tượng Blog với thông tin cần cập nhật
        Blog blog = new Blog();
        blog.setBlog_id(3); // Giả sử blog_id là 1
        blog.setTittle("Updated Blog Title");
        blog.setContent("This is the updated content of the blog.");
        blog.setAuthor_id(2); // Giả sử author_id là 2
        blog.setBrief_infor("This is the updated brief information.");
        blog.setCategory_id(5); // Giả sử category_id là 4
        blog.setStatus(1); // Giả sử status là 1 (Active)

        // Tạo đối tượng BlogDAO để gọi hàm updateBlog
        BlogDAO blogDAO = new BlogDAO();

        // Gọi hàm updateBlog để cập nhật thông tin blog
        blogDAO.updateBlog(blog);

        System.out.println("Blog has been updated successfully.");
    }

}
