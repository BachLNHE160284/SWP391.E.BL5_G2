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
import model.Category;
import model.DashboardDTO;
import model.Service;
import model.ServiceCategoryRevenueDTO;
import model.ServiceFeedbackDTO;

/**
 *
 * @author Admin
 */
public class DashboardDAO extends DBContext {

    public DashboardDTO getStatistic(DashboardDTO dto) {
        try {
            String sql = "SELECT (SELECT  COUNT(reservation_id)\n"
                    + "FROM reservation\n"
                    + "WHERE status_reservation = 0 and create_date = ?) as submittedReservationCount ,\n"
                    + "\n"
                    + "( SELECT  COUNT(reservation_id)\n"
                    + "FROM reservation\n"
                    + "WHERE status_reservation = 1 and create_date = ?) as successReservationCount  ,\n"
                    + "\n"
                    + "\n"
                    + "( SELECT  COUNT(reservation_id)\n"
                    + "FROM reservation\n"
                    + "WHERE status_reservation = 2 and create_date = ?) as cancelledReservationCount  ";
            ResultSet rs = null;
            PreparedStatement ps = null;
            Connection conn = null;

            conn = new DBContext().getConnection(); // Open connection to SQL
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(dto.getNewReservationFilter().getTime()));
            ps.setDate(2, new java.sql.Date(dto.getNewReservationFilter().getTime()));
            ps.setDate(3, new java.sql.Date(dto.getNewReservationFilter().getTime()));
            rs = ps.executeQuery();
            DashboardDTO restult = new DashboardDTO();
            while (rs.next()) {
                int successCount = rs.getInt("successReservationCount");
                int submitCount = rs.getInt("submittedReservationCount");
                int cancelCount = rs.getInt("cancelledReservationCount");
                restult.setSubmittedReservationCount(submitCount);
                restult.setSuccessReservationCount(successCount);
                restult.setCancelledReservationCount(cancelCount);
            }
            sql = "SELECT \n"
                    + "    c.category_id,\n"
                    + "    c.category_name,\n"
                    + "    SUM(r.totalcost) AS revenue\n"
                    + "FROM \n"
                    + "    dbo.category c\n"
                    + "JOIN \n"
                    + "    dbo.service s ON c.category_id = s.category_id\n"
                    + "JOIN \n"
                    + "    dbo.reservation_detail rd ON s.service_id = rd.service_id\n"
                    + "JOIN \n"
                    + "    dbo.reservation r ON rd.reservation_id = r.reservation_id\n"
                    + "WHERE \n"
                    + "    r.create_date = ?\n"
                    + "GROUP BY \n"
                    + "    c.category_id,\n"
                    + "    c.category_name\n"
                    + "HAVING \n"
                    + "    SUM(r.totalcost) > 0;";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(dto.getRevenueFilter().getTime()));
            rs = ps.executeQuery();
            List<ServiceCategoryRevenueDTO> categoryRevenueDTOs = new ArrayList<>();
            ServiceCategoryRevenueDTO categoryRevenueDTO;
            while (rs.next()) {
                categoryRevenueDTO = new ServiceCategoryRevenueDTO();
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                categoryRevenueDTO.setCategory(category);
                categoryRevenueDTO.setRevenue(rs.getDouble("revenue"));
                categoryRevenueDTOs.add(categoryRevenueDTO);
            }
            restult.setRevenues(categoryRevenueDTOs);
            sql = "SELECT \n"
                    + "    s.service_id,\n"
                    + "    s.name_service,\n"
                    + "    AVG(f.rate_star) AS average_star\n"
                    + "FROM \n"
                    + "    dbo.service s\n"
                    + "INNER JOIN \n"
                    + "    dbo.feedback f ON s.service_id = f.service_id\n"
                    + "GROUP BY \n"
                    + "    s.service_id, s.name_service\n"
                    + "HAVING \n"
                    + "    COUNT(f.rate_star) > 0;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ServiceFeedbackDTO> feedbackDTOs = new ArrayList<>();
            while(rs.next()){
                ServiceFeedbackDTO feedbackDTO = new ServiceFeedbackDTO();
                Service service = new Service();
                service.setService_id(rs.getInt("service_id"));
                service.setName_service(rs.getString("name_service"));
                feedbackDTO.setService(service);
                feedbackDTO.setStar(rs.getDouble("average_star"));
                feedbackDTOs.add(feedbackDTO);
            }
            restult.setServiceFeedbacks(feedbackDTOs);
            return restult;
        } catch (Exception ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
