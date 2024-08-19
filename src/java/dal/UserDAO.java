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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import model.User;
import model.UserDTO;

/**
 *
 * @author MinhHieu
 */
public class UserDAO extends DBContext {

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

    public List<UserDTO> getAllUserDto() {
        String query = "SELECT u.* , r.role_name , r.role_id as rid FROM [SWP391BL5G2_4].[dbo].[user] u LEFT JOIN [role] r on u.role_id = r.role_id";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        List<UserDTO> users = new ArrayList<>();
        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setGender(rs.getBoolean("gender"));
                user.setEmail_address(rs.getString("email_address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setAvatar(rs.getString("avartar"));
                Role role = new Role();
                role.setRole_id(rs.getInt("role_id"));
                role.setRole_name(rs.getString("role_name"));
                user.setRole(role);
                user.setStatus(rs.getInt("Status"));
                users.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public int editUser(UserDTO user) {
        String query = "UPDATE [user] SET role_id = ? , status = ? WHERE user_id = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            ps.setInt(1, user.getRole().getRole_id());
            ps.setInt(2, user.getStatus());
            ps.setInt(3, user.getUser_id());
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int addNewUser(User user) {
        try {
            String sql = "INSERT INTO [dbo].[user]\n"
                    + "           ([email_address]\n"
                    + "           ,[username]\n"
                    + "           ,[password]\n"
                    + "           ,[role_id]\n"
                    + "           ,[Status]\n"
                    + "           ,[create_date]) VALUES (? , ? , ? , ? , ? , ?)";
            ResultSet rs = null;
            PreparedStatement ps = null;
            Connection conn = null;
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail_address());
            ps.setString(2, user.getEmail_address());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole_id());
            ps.setInt(5, 1);
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //BachLNHE160284
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [user];";

        try (
                 Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setEmail_address(rs.getString("email_address"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvartar(rs.getString("avartar"));
                user.setRole_id(rs.getInt("role_id"));
                user.setStatus(rs.getInt("Status"));
                user.setCreate_date(rs.getString("create_date"));

                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM [SWP391BL5G2_4].[dbo].[user] WHERE [email_address] = ?";

        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
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
        return null; // Return null if user not found or an exception occurred
    }

    public void updateUser(String fullname, boolean gender, String phone_number, String address, String username, String avartar, int role_id, int Status, String email_address) {
        String query = "UPDATE [SWP391BL5G2_4].[dbo].[user] SET "
                + "[fullname] = ?, "
                + "gender = ?, "
                + "phone_number = ?, "
                + "address = ?, "
                + "username = ?, "
                + "avartar = ?, "
                + "role_id = ?, "
                + "Status = ? "
                + "WHERE email_address = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setBoolean(2, gender);
            ps.setString(3, phone_number);
            ps.setString(4, address);
            ps.setString(5, username);
            ps.setString(6, avartar);
            ps.setInt(7, role_id);
            ps.setInt(8, Status);
            ps.setString(9, email_address);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Test parameters for updating a user
        String fullname = "Jane Doe";
        boolean gender = false; // Female
        String phone_number = "987654321";
        String address = "456 Oak Avenue";
        String username = "janedoe";
        String avartar = "path/to/new_avatar.jpg";
        int role_id = 2; // Assuming role ID 2 is valid
        int status = 0; // Assuming status 0 is inactive
        String email_address = "john@example.com";

        // Call updateUser with the parameters
        userDAO.updateUser(fullname, gender, phone_number, address, username, avartar, role_id, status, email_address);

        // Print a confirmation message
        System.out.println("Update operation completed.");
    }

}
