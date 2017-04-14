package com.mariakomar.slackjsonreader.saver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Get information from Slack API
 *
 * Created by Maria Komar on 11.02.17.
 */
@Service
public class SlackAPIService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Token for Slack API
     */
    @Value("${slack.token}")
    private String token;

    /**
     * Update users username and avatar from Slack API by user id.
     *
     * @param user for which additional information needed
     * @return updated SlackUser
     */
    public SlackUser getAvatarAndName(SlackUser user) throws SaverException {
        RestTemplate restTemplate = new RestTemplate();
        String id = user.getId();
        String message = restTemplate.getForObject
                ("https://slack.com/api/users.info?token="
                        + token
                        + "&user=" + id
                        + "&pretty=1", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(message);
            JsonNode okNode = rootNode.path("ok");
            if (!okNode.asText().equals("true")) {
                throw new SaverException("User not found");
            }
            JsonNode userNode = rootNode.path("user");
            JsonNode nameNode = userNode.path("name");
            JsonNode profileNode = userNode.path("profile");
            JsonNode avatarNode = profileNode.path("image_72");
            user.setAvatar(avatarNode.asText());
            user.setUsername(nameNode.asText());
        } catch (IOException e) {
            logger.warn("User not mapped: {}", message, e);
        }
        logger.info("User updated: {}", user);
        return user;
    }

    /**
     * Retrieve user from Slack API as JSON String.     *
     *
     * @param id of SlackUser
     * @return String with JSON corresponding to specified SlackUser id
     */
    public String getUserAsString(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject
                ("https://slack.com/api/users.info?token="
                        + token
                        + "&user=" + id
                        + "&pretty=1", String.class);
        logger.info("Get data for user: {}", message);
        return message;
    }

}
