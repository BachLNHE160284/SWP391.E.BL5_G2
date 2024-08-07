/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lebac
 */
public class Slider {
    private int slider_id;
    private String slider_img;
    private String backlink;
    private int status;
    private String slider_title;
    private String slider_detail;
    private int update_by;
    private String create_date;

    public Slider() {
    }

    public Slider(int slider_id, String slider_img, String backlink, int status, String slider_title, String slider_detail, int update_by, String create_date) {
        this.slider_id = slider_id;
        this.slider_img = slider_img;
        this.backlink = backlink;
        this.status = status;
        this.slider_title = slider_title;
        this.slider_detail = slider_detail;
        this.update_by = update_by;
        this.create_date = create_date;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public String getSlider_img() {
        return slider_img;
    }

    public void setSlider_img(String slider_img) {
        this.slider_img = slider_img;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSlider_title() {
        return slider_title;
    }

    public void setSlider_title(String slider_title) {
        this.slider_title = slider_title;
    }

    public String getSlider_detail() {
        return slider_detail;
    }

    public void setSlider_detail(String slider_detail) {
        this.slider_detail = slider_detail;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "Slider{" + "slider_id=" + slider_id + ", slider_img=" + slider_img + ", backlink=" + backlink + ", status=" + status + ", slider_title=" + slider_title + ", slider_detail=" + slider_detail + ", update_by=" + update_by + ", create_date=" + create_date + '}';
    }
    
}
