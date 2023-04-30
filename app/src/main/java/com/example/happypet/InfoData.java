package com.example.happypet;

public class InfoData {
    int ino;
    String p_id;
    String title;
    String content;
    String category;
    String img;
    String mk_date;

    public InfoData(int ino, String p_id, String title, String content, String mk_date) {
        this.ino = ino;
        this.p_id = p_id;
        this.title = title;
        this.content = content;
        this.mk_date = mk_date;
    }

    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMk_date() {
        return mk_date;
    }

    public void setMk_date(String mk_date) {
        this.mk_date = mk_date;
    }
}
