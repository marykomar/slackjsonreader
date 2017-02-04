package com.mariakomar.slackjsonreader.model;

import java.util.List;

/**
 * Created by Maria Komar on 29.01.17.
 */
public class SlackUser {
    private String id;
    private String username;
    private String avatar;
    private List<SlackMessage> messages;
    private List<SlackMessage> replies;

    public SlackUser(String id){
        this.id = id;
    }

    public SlackUser(String username, String avatar){
        this.username = username;
        this.avatar = avatar;
    }

    public SlackUser(String username, String avatar, List<SlackMessage> messages){
        this.username = username;
        this.avatar = avatar;
        this.messages = messages;
    }

    public SlackUser(String username, String avatar, List<SlackMessage> messages, List<SlackMessage> replies){
        this.username = username;
        this.avatar = avatar;
        this.messages = messages;
        this.replies = replies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SlackUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", messages=" + messages +
                ", replies=" + replies +
                '}';
    }
}
