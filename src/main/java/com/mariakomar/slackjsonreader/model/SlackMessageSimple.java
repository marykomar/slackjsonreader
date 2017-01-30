package com.mariakomar.slackjsonreader.model;

/**
 * Created by Maria Komar on 30.01.17.
 */
public class SlackMessageSimple {
    private String user;
    private String type;
    private String subtype;
    private String text;
    private String ts;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "SlackMessageSimple{" +
                "user='" + user + '\'' +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", text='" + text + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
