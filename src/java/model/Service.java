/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lebac
 */
public class Service {
    private int service_id;
    private int category_id;
    private String name_service;
    private float original_prices;
    private float sale_prices;
    private int quantity;
    private String category_name;
    private String thumbnail;
    private String brief_infor;
    private String service_detail;
    private String date_add;
    private int service_Status;
    private String create_date;
    private String img_service;
    private Category category;

    public Service() {
    }

    public Service(int service_id, int category_id, String name_service, float original_prices, float sale_prices, int quantity, String category_name, String thumbnail, String brief_infor, String service_detail, String date_add, int service_Status, String create_date, String img_service, Category category) {
        this.service_id = service_id;
        this.category_id = category_id;
        this.name_service = name_service;
        this.original_prices = original_prices;
        this.sale_prices = sale_prices;
        this.quantity = quantity;
        this.category_name = category_name;
        this.thumbnail = thumbnail;
        this.brief_infor = brief_infor;
        this.service_detail = service_detail;
        this.date_add = date_add;
        this.service_Status = service_Status;
        this.create_date = create_date;
        this.img_service = img_service;
        this.category = category;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public float getOriginal_prices() {
        return original_prices;
    }

    public void setOriginal_prices(float original_prices) {
        this.original_prices = original_prices;
    }

    public float getSale_prices() {
        return sale_prices;
    }

    public void setSale_prices(float sale_prices) {
        this.sale_prices = sale_prices;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBrief_infor() {
        return brief_infor;
    }

    public void setBrief_infor(String brief_infor) {
        this.brief_infor = brief_infor;
    }

    public String getService_detail() {
        return service_detail;
    }

    public void setService_detail(String service_detail) {
        this.service_detail = service_detail;
    }

    public String getDate_add() {
        return date_add;
    }

    public void setDate_add(String date_add) {
        this.date_add = date_add;
    }

    public int getService_Status() {
        return service_Status;
    }

    public void setService_Status(int service_Status) {
        this.service_Status = service_Status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getImg_service() {
        return img_service;
    }

    public void setImg_service(String img_service) {
        this.img_service = img_service;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Service{" + "service_id=" + service_id + ", category_id=" + category_id + ", name_service=" + name_service + ", original_prices=" + original_prices + ", sale_prices=" + sale_prices + ", quantity=" + quantity + ", category_name=" + category_name + ", thumbnail=" + thumbnail + ", brief_infor=" + brief_infor + ", service_detail=" + service_detail + ", date_add=" + date_add + ", service_Status=" + service_Status + ", create_date=" + create_date + ", img_service=" + img_service + ", category=" + category + '}';
    }

    

    
    
}
