package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.repositories.SlackMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Maria Komar on 28.02.17.
 */
@Service
public class SlackMessageServiceJpa implements SlackMessageService{
    @Autowired
    private SlackMessageRepository repository;

    @Override
    public List<SlackMessage> getAllMessages() {
        return repository.findAll();
    }

    @Override
    public List<SlackMessage> getMessagesByChannel(String channel) {
        return repository.findByChannel(channel);
    }

    @Override
    public List<SlackMessage> getMessagesByUser(SlackUser user) {
        return repository.findByUser(user);
    }

    @Override
    public SlackMessage createMessage(SlackMessage message) {
        return repository.save(message);
    }
}
