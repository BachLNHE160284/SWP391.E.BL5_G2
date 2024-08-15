/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author lebac
 */
public class Blog {
    private int blog_id;
    private String tittle;
    private String content;
    private int author_id;
    private int update_by;
    private Date update_date;
    private String thumbnail;
    private String brief_infor;
    private int category_id;
    private Date create_date;
    private int status;
    private String category_name;

    public Blog() {
    }

    public Blog(int blog_id, String tittle, String content, int author_id, int update_by, Date update_date, String thumbnail, String brief_infor, int category_id, Date create_date, int status, String category_name) {
        this.blog_id = blog_id;
        this.tittle = tittle;
        this.content = content;
        this.author_id = author_id;
        this.update_by = update_by;
        this.update_date = update_date;
        this.thumbnail = thumbnail;
        this.brief_infor = brief_infor;
        this.category_id = category_id;
        this.create_date = create_date;
        this.status = status;
        this.category_name = category_name;
    }

    public Blog(int blog_id, String tittle, String content, int author_id, int update_by, Date update_date, String thumbnail, String brief_infor, int category_id, Date create_date, int status) {
        this.blog_id = blog_id;
        this.tittle = tittle;
        this.content = content;
        this.author_id = author_id;
        this.update_by = update_by;
        this.update_date = update_date;
        this.thumbnail = thumbnail;
        this.brief_infor = brief_infor;
        this.category_id = category_id;
        this.create_date = create_date;
        this.status = status;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" + "blog_id=" + blog_id + ", tittle=" + tittle + ", content=" + content + ", author_id=" + author_id + ", update_by=" + update_by + ", update_date=" + update_date + ", thumbnail=" + thumbnail + ", brief_infor=" + brief_infor + ", category_id=" + category_id + ", create_date=" + create_date + ", status=" + status + ", category_name=" + category_name + '}';
    }

    

    
    
}
