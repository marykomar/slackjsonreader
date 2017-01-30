package com.mariakomar.slackjsonreader.model;

import java.time.LocalDateTime;

/**
 * Created by Maria Komar on 29.01.17.
 */
public class SlackMessage {
    private SlackUser user;
    private SlackUser respondTo;
    private String text;
    private LocalDateTime time;
    private SlackChannel channel;

    public SlackMessage(SlackUser user, String text, LocalDateTime time, SlackChannel channel){
        this.user = user;
        this.text = text;
        this.time = time;
        this.channel = channel;
    }

    public SlackMessage(SlackUser user, SlackUser respondTo, String text, LocalDateTime time, SlackChannel channel){
        this.user = user;
        this.respondTo = respondTo;
        this.text = text;
        this.time = time;
        this.channel = channel;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
        return "Message{" +
                "user=" + user +
                ", respondTo=" + respondTo +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", channel=" + channel +
                '}';
    }
}
