package com.mariakomar.slackjsonreader.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Maria Komar on 11.02.17.
 */
@Component
public class SlackAPIService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${slack.token}")
    private String token;

    //Get username and avatar from Slack API by user id
    public SlackUser getAvatarAndName(SlackUser user) {
        RestTemplate restTemplate = new RestTemplate();
        String id = user.getId();
        SlackUser updatedUser = new SlackUser();
        String message = restTemplate.getForObject
                ("https://slack.com/api/users.info?token="
                        + token
                        + "&user=" + id
                        + "&pretty=1", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(message);
            JsonNode userNode = rootNode.path("user");
            JsonNode nameNode = userNode.path("name");
            JsonNode profileNode = userNode.path("profile");
            JsonNode avatarNode = profileNode.path("image_72");
            user.setAvatar(avatarNode.asText());
            user.setUsername(nameNode.asText());
        } catch (IOException e) {
            logger.warn("User not mapped");
        }
        logger.info("User updated: " + user);
        return updatedUser;
    }

}
