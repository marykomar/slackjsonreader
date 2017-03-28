package com.mariakomar.slackjsonreader.saver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mariakomar.slackjsonreader.model.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Get users data from Slack and save it to file.
 *
 * Created by Maria Komar on 09.03.17.
 */
@Service
@Lazy
public class UserSaver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private MappingService mappingService;
    private SlackAPIService slackAPIService;
    private FileOperations fos;

    @Autowired
    public UserSaver(MappingService mS, SlackAPIService sS, FileOperations fos) {
        this.mappingService = mS;
        this.slackAPIService = sS;
        this.fos = fos;
    }

    // TODO fix arguments of mappingservice
    /**
     * Get all users id from JSON. Ignore messages without user.
     */
//    public Set<String> getAllUsersId() {
//        Set<String> usersId = new HashSet<>();
//            List<List<SlackMessage>> allMessages = mappingService.readJsonArrayWithObjectMapper();
//            for (List<SlackMessage> messageList : allMessages) {
//                for (SlackMessage message : messageList) {
//                    if (message.getUser() != null && !usersId.contains(message.getUser().getId())) {
//                        usersId.add(message.getUser().getId());
//                    }
//                }
//            }
//            logger.info("User ids found: {}", usersId.size());
//        return usersId;
//    }

//    /**
//     * Stores users information in Strings with JSON
//     */
//    public List<String> getUsersInfoFromSlack() {
//        List<String> allUsers = new LinkedList<>();
//        for (String id : getAllUsersId()) {
//            allUsers.add(slackAPIService.getUserAsString(id));
//        }
//        logger.info("Users info get from slack: {}", allUsers.size());
//        return allUsers;
//    }

//    /**
//     * Save users JSON to file, format it as array.
//     * Current path : "/home/maria/!slack/users.txt"
//     */
//    public void saveUsersJsonToFile(PrintWriter out) {
////        String path = "/home/maria/!slack/users.txt";
////        try (PrintWriter out = new PrintWriter(path)) {
//            out.println("[");
//            for (int i = 0; i < getUsersInfoFromSlack().size() - 1; i++) {
//                out.print(getUsersInfoFromSlack().get(i)
//                        .substring(0, getUsersInfoFromSlack().get(i).length() - 1) + ",\n");
//            }
//            out.print(getUsersInfoFromSlack().get(getUsersInfoFromSlack().size() - 1));
//            out.println("]");
////        } catch (IOException e) {
////            logger.warn("File users.txt not filled", e);
////        }
//        logger.info("Users data written to output");
//    }

    /**
     * Read users from file and map them to List<SlackUser>
     * @return list containing all users from specified file
     * Now saving to "/home/maria/!slack/users.txt"
     */
    // TODO move to different class?
    public List<SlackUser> getUsersFromFile(InputStream inputStream) {
        List<SlackUser> users = new ArrayList<>();
//        File usersFile = new File("/home/maria/!slack/users.txt");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayNode arrNode = objectMapper.readValue(inputStream, ArrayNode.class);
            Iterator<JsonNode> it = arrNode.elements();
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
        } catch (IOException e) {
            logger.warn("Users from file not mapped", e);
        }
        logger.info("Read users from file: {} ", users.size());
        return users;
    }

    /**
     * Save avatars to filesystem.
     * Current path "/home/maria/!slack/avatars/"
     */
    public void saveAvatars(String path, List<SlackUser> users) {
        //String path = "/home/maria/!slack/avatars/";
        for (SlackUser user : users) {
            String url = user.getAvatar();
            String name = user.getId();
            fos.downloadAndSaveWithNIO(url, path + name);
        }
        logger.info("Avatars saved to {}", path);
    }

}
