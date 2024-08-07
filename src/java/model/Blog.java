/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
    private String update_date;
    private String thumbnail;
    private String brief_infor;
    private String category_name;
    private String create_date;
    private String nameAuthor;
    private Boolean status;

    public Blog() {
    }

    public Blog(int blog_id, String tittle, String content, int author_id, int update_by, String update_date, String thumbnail, String brief_infor, String category_name, String create_date, String nameAuthor, Boolean status) {
        this.blog_id = blog_id;
        this.tittle = tittle;
        this.content = content;
        this.author_id = author_id;
        this.update_by = update_by;
        this.update_date = update_date;
        this.thumbnail = thumbnail;
        this.brief_infor = brief_infor;
        this.category_name = category_name;
        this.create_date = create_date;
        this.nameAuthor = nameAuthor;
        this.status = status;
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

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" + "blog_id=" + blog_id + ", tittle=" + tittle + ", content=" + content + ", author_id=" + author_id + ", update_by=" + update_by + ", update_date=" + update_date + ", thumbnail=" + thumbnail + ", brief_infor=" + brief_infor + ", category_name=" + category_name + ", create_date=" + create_date + ", nameAuthor=" + nameAuthor + ", status=" + status + '}';
    }
    
}
