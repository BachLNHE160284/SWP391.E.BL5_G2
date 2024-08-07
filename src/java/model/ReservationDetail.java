/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lebac
 */
public class ReservationDetail {
    private int reservation_detailID;
    private float prices;
    private int quantity;
    private int reservation_id;
    private int service_id;
    private String create_date;
    private Service service;
    private int feedback_Status;

    public ReservationDetail() {
    }

    public ReservationDetail(int reservation_detailID, float prices, int quantity, int reservation_id, int service_id, String create_date, Service service, int feedback_Status) {
        this.reservation_detailID = reservation_detailID;
        this.prices = prices;
        this.quantity = quantity;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.create_date = create_date;
        this.service = service;
        this.feedback_Status = feedback_Status;
    }

    public int getReservation_detailID() {
        return reservation_detailID;
    }

    public void setReservation_detailID(int reservation_detailID) {
        this.reservation_detailID = reservation_detailID;
    }

    public float getPrices() {
        return prices;
    }

    public void setPrices(float prices) {
        this.prices = prices;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getFeedback_Status() {
        return feedback_Status;
    }

    public void setFeedback_Status(int feedback_Status) {
        this.feedback_Status = feedback_Status;
    }

    @Override
    public String toString() {
        return "ReservationDetail{" + "reservation_detailID=" + reservation_detailID + ", prices=" + prices + ", quantity=" + quantity + ", reservation_id=" + reservation_id + ", service_id=" + service_id + ", create_date=" + create_date + ", service=" + service + ", feedback_Status=" + feedback_Status + '}';
    }
    
}
