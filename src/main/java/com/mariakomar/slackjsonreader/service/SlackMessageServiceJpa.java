package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.repositories.SlackMessageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Maria Komar on 28.02.17.
 */

@Primary
@Service
public class SlackMessageServiceJpa implements SlackMessageService{
    //@Autowired
    private SlackMessageRepository repository;

    @Override
    public List<SlackMessage> getAllMessages() {
        return repository.findAll();
    }

//    @Override
//    public List<SlackMessage> getMessagesByChannel(SlackChannel channel) {
//        return repository.findByChannel(channel);
//    }

    @Override
    public List<SlackMessage> getMessagesByUser(SlackUser user) {
        return repository.findByUser(user);
    }

    @Override
    public SlackMessage createMessage(SlackMessage message) {
        return repository.save(message);
    }
}
