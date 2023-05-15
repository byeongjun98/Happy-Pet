package com.example.happypet;

public class FreeConsultingData {
    int fno;
    String p_id;
    String title;
    String question;
    String category;
    String image;
    String reg_date;

    public FreeConsultingData(int fno, String p_id, String title, String question, String category, String image, String reg_date) {
        this.fno = fno;
        this.p_id = p_id;
        this.title = title;
        this.question = question;
        this.category = category;
        this.image = image;
        this.reg_date = reg_date;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
