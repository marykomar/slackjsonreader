package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Temporary class for UI testing.
 * Created by Maria Komar on 29.01.17.
 */
@Service
@Primary
public class SlackMessageServiceStubImpl implements SlackMessageService {
    private SlackUser user1 = new SlackUser("user1", "avatar");
    private SlackUser user2 = new SlackUser("user2", "avatar2");
    private SlackMessage message1 = new SlackMessage(user1, "message1", LocalDateTime.now(), "channel");
    private SlackMessage message2 = new SlackMessage(user2, "message2", LocalDateTime.now(), "channel");
    private List<SlackMessage> messages = new ArrayList<>(Arrays.asList(message1, message2));

    @Override
    public List<SlackMessage> getAllMessages() {
        return messages;
    }

    @Override
    public List<SlackMessage> getMessagesByChannel(String channel) {
        return messages;
    }

    @Override
    public List<SlackMessage> getMessagesByUser(SlackUser user) {
        return messages.stream().filter(m -> Objects.equals(m.getUser(), user))
                .collect(Collectors.toList());
    }

    @Override
    public SlackMessage createMessage(SlackMessage message) {
        return null;
    }

    public List<SlackMessage> getReplies(SlackUser user) {
        return messages;
    }
}
