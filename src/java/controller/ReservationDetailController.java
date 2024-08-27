/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dal.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReservationDetailDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ReservationDetailController", urlPatterns = {"/staff-reservation-detail"})
public class ReservationDetailController extends HttpServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() throws ServletException {
        reservationDAO = new ReservationDAO(); // Initialize your DAO
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the reservation ID from the request parameter
        String reservationIdParam = request.getParameter("reservationId");

        // Validate the reservation ID
        if (reservationIdParam == null || reservationIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Reservation ID is required.");
            return;
        }

        try {
            int reservationId = Integer.parseInt(reservationIdParam);

            // Fetch the reservation details from the database using the DAO
            ReservationDetailDTO reservationDetail = reservationDAO.getReservationDetail(reservationId);

            // Check if reservation details are found
            if (reservationDetail != null) {
                // Convert the reservation detail object to JSON
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(reservationDetail);

                // Set response type and write the JSON response
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.write(jsonResponse);
                out.flush();
            } else {
                // If no data is found, return a 404 status
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Reservation not found.");
            }
        } catch (NumberFormatException e) {
            // Handle invalid reservation ID format
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid reservation ID format.");
        } catch (Exception e) {
            // Handle general exceptions
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred while fetching the reservation details.");
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
