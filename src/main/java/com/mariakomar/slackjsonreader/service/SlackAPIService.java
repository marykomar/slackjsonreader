package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Maria Komar on 11.02.17.
 */
@Component
public class SlackAPIService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${slack.token}")
    private String token;

    //Get username and avatar from Slack API by user id
    public SlackUser getAvatarAndName(SlackUser user){
        RestTemplate restTemplate = new RestTemplate();
        String id = user.getId();
        String message = restTemplate.getForObject
                ("https://slack.com/api/users.info?token="
                        + token
                        + "&user=" + id
                        + "&pretty=1", String.class);
        logger.info(message + " t: " + token);
        user.setUsername(message);
        return user;
    }

    public String getToken(){
        return token;
    }
}
