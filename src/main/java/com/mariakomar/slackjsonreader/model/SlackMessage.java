package com.mariakomar.slackjsonreader.model;


import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mariakomar.slackjsonreader.service.CustomDateDeserializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Maria Komar on 30.01.17.
 */

@Entity
@Table(name="messages")
public class SlackMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SlackUser user;

    @Column(length = 100, nullable = false)
    private String type;

    @Column(length = 100, nullable = false)
    private String subtype;

    @Lob
    @Column(nullable = false)
    private String text;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(nullable = false)
    private LocalDateTime ts;

    @JacksonInject
    @Enumerated(EnumType.ORDINAL)
    private SlackChannel channel;

    public SlackMessage(SlackUser user, String text, LocalDateTime ts, SlackChannel channel){
        this.user = user;
        this.text = text;
        this.ts = ts;
        this.channel = channel;
    }

    public SlackMessage(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
