package com.mariakomar.slackjsonreader.model;


import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mariakomar.slackjsonreader.service.CustomDateDeserializer;
import java.time.LocalDateTime;

/**
 * Created by Maria Komar on 30.01.17.
 */
public class SlackMessage {
    private SlackUser user;
    private String type;
    private String subtype;
    private String text;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime ts;
    @JacksonInject
    private SlackChannel channel;

    public SlackMessage(SlackUser user, String text, LocalDateTime ts, SlackChannel channel){
        this.user = user;
        this.text = text;
        this.ts = ts;
        this.channel = channel;
    }

    public SlackMessage(){

    }

    public SlackUser getUser() {
        return user;
    }

    public void setUser(SlackUser user) {
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

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public SlackChannel getChannel() {
        return channel;
    }

    public void setChannel(SlackChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "SlackMessageSimple{" +
                "user=" + user +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", text='" + text + '\'' +
                ", ts=" + ts +
                ", channel=" + channel +
                '}';
    }
}
