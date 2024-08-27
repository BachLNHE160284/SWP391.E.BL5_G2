/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

public class ReservationDetailDTO {
    // Basic reservation information
    private int reservationId;
    private String customerFullName;
    private String customerEmail;
    private String customerMobile;
    private Date reservationDate;
    private double totalCost;
    private int statusReservation;

    // Receiver information
    private String receiverFullName;
    private boolean receiverGender;
    private String receiverEmail;
    private String receiverMobile;
    private String receiverAddress;

    // Reserved services
    private List<ReservedService> reservedServices;

    // Getters and Setters

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(int statusReservation) {
        this.statusReservation = statusReservation;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public void setReceiverFullName(String receiverFullName) {
        this.receiverFullName = receiverFullName;
    }

    public boolean isReceiverGender() {
        return receiverGender;
    }

    public void setReceiverGender(boolean receiverGender) {
        this.receiverGender = receiverGender;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<ReservedService> getReservedServices() {
        return reservedServices;
    }

    public void setReservedServices(List<ReservedService> reservedServices) {
        this.reservedServices = reservedServices;
    }

    // Nested class to represent reserved services
    public static class ReservedService {
        private String serviceThumbnail;
        private String serviceName;
        private String categoryName;
        private double unitPrice;
        private int numberOfPerson;
        private double serviceTotalCost;

        // Getters and Setters
        public String getServiceThumbnail() {
            return serviceThumbnail;
        }

        public void setServiceThumbnail(String serviceThumbnail) {
            this.serviceThumbnail = serviceThumbnail;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getNumberOfPerson() {
            return numberOfPerson;
        }

        public void setNumberOfPerson(int numberOfPerson) {
            this.numberOfPerson = numberOfPerson;
        }

        public double getServiceTotalCost() {
            return serviceTotalCost;
        }

        public void setServiceTotalCost(double serviceTotalCost) {
            this.serviceTotalCost = serviceTotalCost;
        }
    }
}
