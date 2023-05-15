package com.example.happypet;

public class FreeConsultingCommentData {
    int fcno;
    int fno;
    String comment;
    String commenter;
    String reg_date;

    public FreeConsultingCommentData(int fcno, int fno, String comment, String commenter, String reg_date) {
        this.fcno = fcno;
        this.fno = fno;
        this.comment = comment;
        this.commenter = commenter;
        this.reg_date = reg_date;
    }

    public int getFcno() {
        return fcno;
    }

    public void setFcno(int fcno) {
        this.fcno = fcno;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
