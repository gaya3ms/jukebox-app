package com.g3code.jukebox.models;

import java.util.Date;

public class APIResponseWrapper {
    private Date timestamp;
    private String data;

    public APIResponseWrapper(String data){
        this.timestamp = new Date();
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
