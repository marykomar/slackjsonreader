package com.mariakomar.slackjsonreader.saver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserialize json with Jackson
 *
 * Created by Maria Komar on 30.01.17.
 */
@Service
public class MappingServiceJackson implements MappingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${slack.directory}")
    private String mainDirectoryPath;

    public String getMainDirectoryPath() {
        return mainDirectoryPath;
    }

    public void setMainDirectoryPath(String mainDirectoryPath) {
        this.mainDirectoryPath = mainDirectoryPath;
    }

    // Current path "/home/maria/!slack/json"

    /**
     * Read files with json from filesystem.
     * Path to main folder specified in .properties.
     * SlackChannel is defined by folder name which contains files.
     *
     * @return all found messages
     * @throws SaverException in case of wrong path
     */
    public List<SlackMessage> readJsonArrayWithObjectMapper() throws SaverException {
        List<SlackMessage> allMessages = new ArrayList<>();
        File mainDirectory = new File(mainDirectoryPath);
        File[] directories = mainDirectory.listFiles();
        if (directories == null || directories.length == 0) {
            throw new SaverException("Main directory not exist or do not contain subdirectories");
        }

        for (File dir : directories) {
            String dirName = dir.getName();
            File[] jsonFiles = dir.listFiles();
            for (File file : jsonFiles) {
                String fileName = file.getAbsolutePath();
                allMessages.addAll(jsonReader(new File(fileName), dirName));
            }

        }

        logger.info("Found messages: {}", allMessages.size());
        return allMessages;
    }

    // Mapping json to List<SlackMessage>
    private List<SlackMessage> jsonReader(File file, String channel) {
        List<SlackMessage> sl = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InjectableValues inject = new InjectableValues.Std()
                .addValue(String.class, channel);
        try {
            sl = objectMapper
                    .reader(inject)
                    .forType(new TypeReference<List<SlackMessage>>() {
                    })
                    .readValue(file);
        } catch (IOException e) {
            logger.warn("Json not mapped", e);
        }
        // logger.info("For channel {} found {} messages", channel, sl.size());
        return sl;
    }

}
