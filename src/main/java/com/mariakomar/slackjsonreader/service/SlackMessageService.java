package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;

import java.util.List;

/**
 * Created by Maria Komar on 29.01.17.
 */
public interface SlackMessageService {
    List<SlackMessage> getAllMessages();

    List<SlackMessage> getMessagesByChannel(String channel);

    List<SlackMessage> getMessagesByUser(SlackUser user);

    SlackMessage createMessage(SlackMessage message);
}
