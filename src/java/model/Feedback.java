/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lebac
 */
public class Feedback {
    private int feedback_id;
    private String feedback_img;
    private String feedback; 
    private float rate_star;
    private int service_id;
    private int user_id;
    private boolean feedback_status;
    private String create_date;
    private Service service;
    private User user;

    public Feedback() {
    }

    public Feedback(int feedback_id, String feedback_img, String feedback, float rate_star, int service_id, int user_id, boolean feedback_status, String create_date, Service service, User user) {
        this.feedback_id = feedback_id;
        this.feedback_img = feedback_img;
        this.feedback = feedback;
        this.rate_star = rate_star;
        this.service_id = service_id;
        this.user_id = user_id;
        this.feedback_status = feedback_status;
        this.create_date = create_date;
        this.service = service;
        this.user = user;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_img() {
        return feedback_img;
    }

    public void setFeedback_img(String feedback_img) {
        this.feedback_img = feedback_img;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getRate_star() {
        return rate_star;
    }

    public void setRate_star(float rate_star) {
        this.rate_star = rate_star;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isFeedback_status() {
        return feedback_status;
    }

    public void setFeedback_status(boolean feedback_status) {
        this.feedback_status = feedback_status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedback_id=" + feedback_id + ", feedback_img=" + feedback_img + ", feedback=" + feedback + ", rate_star=" + rate_star + ", service_id=" + service_id + ", user_id=" + user_id + ", feedback_status=" + feedback_status + ", create_date=" + create_date + ", service=" + service + ", user=" + user + '}';
    }
    
    
}
