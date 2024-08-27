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
import model.ReservationDetailDTO;

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
            while (rs.next()) {
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

    public ReservationDetailDTO getReservationDetail(int reservationId) {
        String query = "SELECT r.reservation_id, u.fullname AS customer_fullname, u.email_address AS customer_email, "
                + "u.phone_number AS customer_mobile, r.reservation_date, r.totalcost, "
                + "r.status_reservation, "
                + "r.fullname AS receiver_fullname, r.gender AS receiver_gender, r.email AS receiver_email, "
                + "r.mobile AS receiver_mobile, r.address AS receiver_address, "
                + "srv.thumbnail, srv.name_service, c.category_name, rd.prices, rd.quantity, "
                + "(rd.prices * rd.quantity) AS service_total_cost "
                + "FROM reservation r "
                + "LEFT JOIN [user] u ON r.user_id = u.user_id "
                + "LEFT JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id "
                + "LEFT JOIN service srv ON rd.service_id = srv.service_id "
                + "LEFT JOIN category c ON srv.category_id = c.category_id "
                + "WHERE r.reservation_id = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(query);
            ps.setInt(1, reservationId);
            rs = ps.executeQuery();

            ReservationDetailDTO reservationDetail = null;
            List<ReservationDetailDTO.ReservedService> reservedServices = new ArrayList<>();

            while (rs.next()) {
                if (reservationDetail == null) {
                    // Initialize ReservationDetailDTO if it's the first row
                    reservationDetail = new ReservationDetailDTO();
                    reservationDetail.setReservationId(rs.getInt("reservation_id"));
                    reservationDetail.setCustomerFullName(rs.getString("customer_fullname"));
                    reservationDetail.setCustomerEmail(rs.getString("customer_email"));
                    reservationDetail.setCustomerMobile(rs.getString("customer_mobile"));
                    reservationDetail.setReservationDate(rs.getDate("reservation_date"));
                    reservationDetail.setTotalCost(rs.getDouble("totalcost"));
                    reservationDetail.setStatusReservation(rs.getInt("status_reservation"));
                    reservationDetail.setReceiverFullName(rs.getString("receiver_fullname"));
                    reservationDetail.setReceiverGender(rs.getBoolean("receiver_gender"));
                    reservationDetail.setReceiverEmail(rs.getString("receiver_email"));
                    reservationDetail.setReceiverMobile(rs.getString("receiver_mobile"));
                    reservationDetail.setReceiverAddress(rs.getString("receiver_address"));
                }

                // Populate reserved services
                String nameService = rs.getString("name_service");
                if(nameService == null) continue;
                ReservationDetailDTO.ReservedService service = new ReservationDetailDTO.ReservedService();
                service.setServiceThumbnail(rs.getString("thumbnail"));
                service.setServiceName(nameService);
                service.setCategoryName(rs.getString("category_name"));
                service.setUnitPrice(rs.getDouble("prices"));
                service.setNumberOfPerson(rs.getInt("quantity"));
                service.setServiceTotalCost(rs.getDouble("service_total_cost"));

                reservedServices.add(service);
            }

            if (reservationDetail != null) {
                reservationDetail.setReservedServices(reservedServices);
            }

            return reservationDetail;
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
        return null; // Return null if retrieval fails or an exception occurred
    }
}
