package com.example.happypet;

public class DoctorData {
    String name;
    String star_rating;
    String subject;
    String hospital;

    public DoctorData(String name, String star_rating, String subject, String hospital) {
        this.name = name;
        this.star_rating = star_rating;
        this.subject = subject;
        this.hospital = hospital;
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
