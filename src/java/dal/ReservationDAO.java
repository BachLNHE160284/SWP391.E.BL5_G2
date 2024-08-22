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
import model.ReservationDTO;

/**
 *
 * @author Admin
 */
public class ReservationDAO extends DBContext {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<ReservationDTO> getAll(String from, String to) {
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        try {
            String sql = "SELECT r.reservation_id ,  r.totalcost , r.reservation_date , u.email_address , r.status_reservation\n"
                    + "FROM reservation r \n"
                    + "left join [user] u on r.user_id = u.user_id\n"
                    + "WHERE r.reservation_date >= ? and r.reservation_date <= ?";
            ResultSet rs = null;
            PreparedStatement ps = null;
            Connection conn = null;

            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, from);
            ps.setString(2, to);
            rs = ps.executeQuery();
            while(rs.next()){
               ReservationDTO dTO = new ReservationDTO();
               dTO.setId(rs.getInt("reservation_id"));
               dTO.setTotalCost(rs.getDouble("totalcost"));
               dTO.setReservationDate(rs.getDate("reservation_date"));
               dTO.setUserMail(rs.getString("email_address"));
               dTO.setStatus(rs.getInt("status_reservation"));
               reservationDTOs.add(dTO);
            }
            int b = 1;
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservationDTOs;
    }
    
    public void updateReservation(int serviceID, int quantity) {
        String sql = "UPDATE Reservation SET quantity = ? WHERE service_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, serviceID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int serviceID) {
        String sql = "DELETE FROM Reservation WHERE service_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, serviceID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
