package com.mariakomar.slackjsonreader.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Get users data from Slack and save it to file.
 *
 * Created by Maria Komar on 09.03.17.
 */
@Component
public class UserSaver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Set<String> usersId = new HashSet<>();
    private List<String> allUsers = new LinkedList<>();

    /**
     * Get all users id from JSON
     *
     * @param mappingService reads JSON from files
     */
    @Autowired
    public void getAllUsersId(MappingService mappingService) {
        try {
            List<List<SlackMessage>> allMessages = mappingService.readJsonArrayWithObjectMapper();
            for (List<SlackMessage> messageList : allMessages) {
                for (SlackMessage message : messageList) {
                    if (message.getUser() != null && !usersId.contains(message.getUser().getId())) {
                        usersId.add(message.getUser().getId());
                    } else logger.info("message without user " + message);
                }
            }
            logger.info("ids " + usersId + " quantity " + usersId.size());
        } catch (IOException e) {
            logger.warn("Messages not get from slack archive");
        }
    }

    /**
     * Stores users information in Strings with JSON
     *
     * @param slackAPIService retrieves users data from Slack
     */
    @Autowired
    public void getUsersInfoFromSlack(SlackAPIService slackAPIService) {
        for (String id : usersId) {
            allUsers.add(slackAPIService.getUserAsString(id));
            logger.info("user :" + allUsers.get(0));
        }
    }

    /**
     * Save users JSON to file, format it as array.
     */
    public void saveUsersJsonToFile() {
        try (PrintWriter out = new PrintWriter("/home/maria/!slack/users.txt")) {
            out.println("[");
            for (int i = 0; i < allUsers.size() - 1; i++) {
                out.print(allUsers.get(i).substring(0, allUsers.get(i).length() - 1) + ",\n");
            }
            out.print(allUsers.get(allUsers.size() - 1));
            out.println("]");
        } catch (IOException e) {
            logger.warn("users.txt not filled");
        }
    }

    /**
     * Read users from file and map them to List<SlackUser>
     *
     * @return list containing all users from specified file
     */
    // TODO move to different class?
    public List<SlackUser> getUsersFromFile() {
        List<SlackUser> users = new ArrayList<>();
        File usersFile = new File("/home/maria/!slack/users.txt");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayNode arrNode = objectMapper.readValue(usersFile, ArrayNode.class);
            Iterator<JsonNode> it = arrNode.elements();
            logger.info("Array node");
            while (it.hasNext()) {
                JsonNode node = it.next();
                JsonNode userNode = node.get("user");
                SlackUser user = new SlackUser();
                user.setId(userNode.get("id").asText());
                user.setUsername(userNode.get("name").asText());
                JsonNode profile = userNode.get("profile");
                user.setAvatar(profile.get("image_72").asText());
                users.add(user);
            }
            logger.info("users " + users);
        } catch (IOException e) {
            logger.warn("Users from file not mapped");
        }
        return users;
    }

    /**
     * Save avatars to filesystem.
     *
     * @param fos contains methods for saving to filesystem
     */
    @Autowired
    public void saveAvatars(FileOperations fos) {
        for (SlackUser user : getUsersFromFile()) {
            String url = user.getAvatar();
            String path = "/home/maria/!slack/avatars/";
            String name = user.getId();
            try {
                fos.downloadAndSaveWithNIO(url, path + name);
            } catch (IOException e) {
                logger.warn("Avatars not saved");
            }
        }
    }

}
