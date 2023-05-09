package com.example.happypet;

public class DoctorData {
    int dno;
    String name;
    String star_rating;
    String subject;
    String hospital;

    public DoctorData(int dno, String name, String star_rating, String subject, String hospital) {
        this.dno = dno;
        this.name = name;
        this.star_rating = star_rating;
        this.subject = subject;
        this.hospital = hospital;
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
}
