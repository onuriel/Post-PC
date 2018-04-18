package com.example.orenn.chat;

import java.util.Date;

public class Message {
    private String mName, mMessage;
    private Date time;

    public Message(String mName, String mMessage) {
        this.mName = mName;
        this.mMessage = mMessage;
        this.time = new Date();
    }

    public String getmMessage() {
        return mMessage;
    }

    public String getmName() {
        return mName;
    }

    public Date getTime() {
        return time;
    }
}
