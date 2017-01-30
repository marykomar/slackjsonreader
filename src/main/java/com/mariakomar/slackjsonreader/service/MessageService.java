package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackChannel;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;

import java.util.List;

/**
 * Created by Maria Komar on 29.01.17.
 */
public interface MessageService {
    public List<SlackMessage> getAllMessages();
    public List<SlackMessage> getMessagesByChannel(SlackChannel channel);
    public List<SlackMessage> getMessagesByUser(SlackUser user);
    public List<SlackMessage> getReplies(SlackUser user);
}
