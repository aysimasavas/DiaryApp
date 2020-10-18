package com.aysimasavas.dailyapplication;

import java.util.Date;

public class DailyModel {

    private String note;
    private Date date;

    public DailyModel(String note, Date date) {
        this.note = note;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
