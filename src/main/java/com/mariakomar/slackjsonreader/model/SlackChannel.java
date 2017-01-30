package com.mariakomar.slackjsonreader.model;

import java.util.List;

/**
 * Created by Maria Komar on 29.01.17.
 */
public class SlackChannel {
    private String name;
    private List<SlackMessage> messages;

    public SlackChannel(String name){
        this.name = name;
    }

    public SlackChannel(String name, List<SlackMessage> messages){
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SlackMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<SlackMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", messages=" + messages +
                '}';
    }
}
