package com.mariakomar.slackjsonreader.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariakomar.slackjsonreader.model.SlackChannel;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackMessageSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maria Komar on 30.01.17.
 */
public class MappingServiceJackson implements MappingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void readJsonArrayWithObjectMapper() throws IOException{
        File mainDirectory = new File("/home/maria/json");
        //Read subfolders names, by names define enum, deserialize all files from subfolder
        File[] directories = mainDirectory.listFiles();
        for(File dir: directories){
            String dirName = dir.getName();
            File[] jsonFiles = dir.listFiles();
            for(File file: jsonFiles) {
                String fileName = file.getAbsolutePath();
                switch (dirName) {
                    case "general":
                        List<SlackMessageSimple> ls =
                                jsonReader(new File(fileName), SlackChannel.GENERAL);
                        break;
                    case "beginner":
                        jsonReader(new File(fileName), SlackChannel.BEGINNER);
                        break;
                    case "javaquestions":
                        jsonReader(new File(fileName), SlackChannel.JAVAQUESTIONS);
                        break;
                    case "javatasks":
                        jsonReader(new File(fileName), SlackChannel.JAVATASKS);
                        break;
                    case "links":
                        jsonReader(new File(fileName), SlackChannel.LINKS);
                        break;
                    default:
                        logger.warn("Unknown directory " + dirName);
                }
            }
        }
    }



    public List<SlackMessageSimple> jsonReader(File file, SlackChannel channel) throws IOException{
        List<SlackMessageSimple> sl;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InjectableValues inject = new InjectableValues.Std()
                .addValue(SlackChannel.class, channel);
        sl = objectMapper
                .reader(inject)
                .forType(new TypeReference<List<SlackMessageSimple>>() {})
                .readValue(file);
        logger.info("array " + sl.toString());
        return sl;
    }
}
