package com.marwa.chat.DB.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity
public class Msg {

    @PrimaryKey(autoGenerate = true)
    private int idMsg;
    private String msg;
    private Date dateMsg;

    public Msg() {
    }

    @Ignore
    public Msg(String msg, Date dateMsg) {
        this.msg = msg;
        this.dateMsg = dateMsg;
    }

    public int getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(int idMsg) {
        this.idMsg = idMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDateMsg() {
        return dateMsg;
    }

    public void setDateMsg(Date dateMsg) {
        this.dateMsg = dateMsg;
    }
}
