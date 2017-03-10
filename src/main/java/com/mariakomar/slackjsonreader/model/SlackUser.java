package com.mariakomar.slackjsonreader.model;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class with parameters of slack user.
 * Names of fields corresponds to names from json.
 *
 * Created by Maria Komar on 29.01.17.
 */
@Entity
@Table(name="users")
public class SlackUser {
    @Id
    private String id;

    @Column(length = 100, unique = true)
    private String username;

    @Column
    private String avatar;

    @OneToMany(mappedBy="user")
    private List<SlackMessage> messages;

    public SlackUser(){

    }

    public SlackUser(String id){
        this.id = id;
    }

    public SlackUser(String username, String avatar){
        this.username = username;
        this.avatar = avatar;
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
                '}';
    }
}
