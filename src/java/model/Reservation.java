/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lebac
 */
public class Reservation {
    private int reservation_id;
    private String reservation_date;
    private int user_id;
    private String fullname;
    private String email;
    private String mobile;
    private String address;
    private int status_reservation;
    private String create_date;
    private double totalCost;
    private String firstReservationName;
    private String statusName;
    private int gender;
    private int sale_id;
    private int numberService;

    public Reservation() {
    }

    public Reservation(int reservation_id, String reservation_date, int user_id, String fullname, String email, String mobile, String address, int status_reservation, String create_date, double totalCost, String firstReservationName, String statusName, int gender, int sale_id, int numberService) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.status_reservation = status_reservation;
        this.create_date = create_date;
        this.totalCost = totalCost;
        this.firstReservationName = firstReservationName;
        this.statusName = statusName;
        this.gender = gender;
        this.sale_id = sale_id;
        this.numberService = numberService;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus_reservation() {
        return status_reservation;
    }

    public void setStatus_reservation(int status_reservation) {
        this.status_reservation = status_reservation;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getFirstReservationName() {
        return firstReservationName;
    }

    public void setFirstReservationName(String firstReservationName) {
        this.firstReservationName = firstReservationName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getNumberService() {
        return numberService;
    }

    public void setNumberService(int numberService) {
        this.numberService = numberService;
    }

    @Override
    public String toString() {
        return "Reservation{" + "reservation_id=" + reservation_id + ", reservation_date=" + reservation_date + ", user_id=" + user_id + ", fullname=" + fullname + ", email=" + email + ", mobile=" + mobile + ", address=" + address + ", status_reservation=" + status_reservation + ", create_date=" + create_date + ", totalCost=" + totalCost + ", firstReservationName=" + firstReservationName + ", statusName=" + statusName + ", gender=" + gender + ", sale_id=" + sale_id + ", numberService=" + numberService + '}';
    }
    
    
}
