package com.mariakomar.slackjsonreader.model;

import java.time.LocalDateTime;

/**
 * Created by Maria Komar on 29.01.17.
 */
public class SlackMessage {
    private SlackUser user;
    private String type;
    private String subtype;
    private String text;
    private LocalDateTime ts;
    private SlackChannel channel;
    private SlackUser respondTo;

    public SlackMessage(){

    }

    public SlackMessage(SlackUser user, String text, LocalDateTime time, SlackChannel channel){
        this.user = user;
        this.text = text;
        this.ts = time;
        this.channel = channel;
    }

    public SlackMessage(SlackUser user, SlackUser respondTo, String text, LocalDateTime time, SlackChannel channel){
        this.user = user;
        this.respondTo = respondTo;
        this.text = text;
        this.ts = time;
        this.channel = channel;
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

    public SlackUser getUser() {
        return user;
    }

    public void setUser(SlackUser user) {
        this.user = user;
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

    public SlackUser getRespondTo() {
        return respondTo;
    }

    public void setRespondTo(SlackUser respondTo) {
        this.respondTo = respondTo;
    }

    @Override
    public String toString() {
        return "SlackMessage{" +
                "user=" + user +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", text='" + text + '\'' +
                ", ts=" + ts +
                ", channel=" + channel +
                ", respondTo=" + respondTo +
                '}';
    }
}
