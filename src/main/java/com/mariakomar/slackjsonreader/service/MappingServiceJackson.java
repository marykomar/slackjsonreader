package com.mariakomar.slackjsonreader.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariakomar.slackjsonreader.model.SlackChannel;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserialize json with Jackson
 *
 * Created by Maria Komar on 30.01.17.
 */
@Component
public class MappingServiceJackson implements MappingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Read files with json from filesystem.
     * SlackChannel is defined by folder name which contains files.
     *
     * @return list of lists grouped by SlackChannel
     * @throws IOException when file read is unsuccessful
     */
    public List<List<SlackMessage>> readJsonArrayWithObjectMapper() throws IOException{
        List<List<SlackMessage>> allMessages = new ArrayList<>();
        File mainDirectory = new File("/home/maria/!slack/json");
        // Read subfolders names, by names define enum, deserialize all files from subfolder
        File[] directories = mainDirectory.listFiles();
        if (directories != null) {
            for (File dir : directories) {
                String dirName = dir.getName();
                File[] jsonFiles = dir.listFiles();
                for (File file : jsonFiles) {
                    String fileName = file.getAbsolutePath();
                    switch (dirName) {
                        case "general":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.GENERAL));
                            break;
                        case "beginner":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.BEGINNER));
                            break;
                        case "javaquestions":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.JAVAQUESTIONS));
                            break;
                        case "javatasks":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.JAVATASKS));
                            break;
                        case "links":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.LINKS));
                            break;
                        case "mentoring":
                            allMessages.add(
                                    jsonReader(new File(fileName), SlackChannel.MENTORING));
                            break;
                        default:
                            logger.warn("Unknown directory " + dirName);
                    }
                }
            }
        }
        return allMessages;
    }

    // Mapping json to List<SlackMessage>
    private List<SlackMessage> jsonReader(File file, SlackChannel channel) throws IOException {
        List<SlackMessage> sl;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InjectableValues inject = new InjectableValues.Std()
                .addValue(SlackChannel.class, channel);
        sl = objectMapper
                .reader(inject)
                .forType(new TypeReference<List<SlackMessage>>() {})
                .readValue(file);
        logger.info("first message " + sl.get(0));
        return sl;
    }

}
