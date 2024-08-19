/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DashboardDTO {
    private Date newReservationFilter;
    private Date revenueFilter;
    private List<ServiceCategoryRevenueDTO> revenues;
    private int successReservationCount;
    private int cancelledReservationCount;
    private int submittedReservationCount;
    private List<ServiceFeedbackDTO> serviceFeedbacks;

    public Date getNewReservationFilter() {
        return newReservationFilter;
    }

    public void setNewReservationFilter(Date newReservationFilter) {
        this.newReservationFilter = newReservationFilter;
    }

    public Date getRevenueFilter() {
        return revenueFilter;
    }

    public void setRevenueFilter(Date revenueFilter) {
        this.revenueFilter = revenueFilter;
    }

    public List<ServiceCategoryRevenueDTO> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<ServiceCategoryRevenueDTO> revenues) {
        this.revenues = revenues;
    }

    public int getSuccessReservationCount() {
        return successReservationCount;
    }

    public void setSuccessReservationCount(int successReservationCount) {
        this.successReservationCount = successReservationCount;
    }

    public int getCancelledReservationCount() {
        return cancelledReservationCount;
    }

    public void setCancelledReservationCount(int cancelledReservationCount) {
        this.cancelledReservationCount = cancelledReservationCount;
    }

    public int getSubmittedReservationCount() {
        return submittedReservationCount;
    }

    public void setSubmittedReservationCount(int submittedReservationCount) {
        this.submittedReservationCount = submittedReservationCount;
    }

    public List<ServiceFeedbackDTO> getServiceFeedbacks() {
        return serviceFeedbacks;
    }

    public void setServiceFeedbacks(List<ServiceFeedbackDTO> serviceFeedbacks) {
        this.serviceFeedbacks = serviceFeedbacks;
    }
    
    
    
    
}
