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
public class MappingServiceImpl implements MappingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public SlackMessageSimple readJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InjectableValues inject = new InjectableValues.Std()
                .addValue(SlackChannel.class, SlackChannel.BEGINNER);
         SlackMessageSimple slackMessage = objectMapper
                 .reader(inject)
                 .forType(SlackMessageSimple.class)
                .readValue(new File(getClass().getResource("/json/beginner/111test-3.json").getFile()));
        logger.info("one " + slackMessage.toString());
        return slackMessage;
    }

    public List<SlackMessageSimple> readJsonArrayWithObjectMapper() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File mainDirectory = new File("/home/maria/json");
        logger.info(String.valueOf(mainDirectory.isDirectory()));
        //Read subfolders names, by names define enum, deserialize all files from subdirectory
        File[] directories = mainDirectory.listFiles();
        for(File dir: directories){
            String fileName = dir.getName();
            SlackChannel channel;
            switch (fileName){
                default: logger.info(fileName);
            }
        }
        InjectableValues inject = new InjectableValues.Std()
                .addValue(SlackChannel.class, SlackChannel.BEGINNER);
        List<SlackMessageSimple> sl = objectMapper
                .reader(inject)
                .forType(new TypeReference<List<SlackMessageSimple>>(){})
                .readValue(new File(getClass().getResource("/json/beginner/2016-05-07.json").getFile()));
        logger.info("array " + sl.toString());
        return sl;
    }
}
