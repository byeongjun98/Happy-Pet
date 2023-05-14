package com.example.happypet;

public class DoctorData {
    int dno;
    String name;
    String star_rating;
    String subject;
    String hospital;
    int customer_num;

    public DoctorData(int dno, String name, String star_rating, String subject, String hospital, int customer_num) {
        this.dno = dno;
        this.name = name;
        this.star_rating = star_rating;
        this.subject = subject;
        this.hospital = hospital;
        this.customer_num = customer_num;
    }

    public int getDno() {
        return dno;
    }

    public void setDno(int dno) {
        this.dno = dno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(String star_rating) {
        this.star_rating = star_rating;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getCustomer_num() {
        return customer_num;
    }

    public void setCustomer_num(int customer_num) {
        this.customer_num = customer_num;
    }
}
