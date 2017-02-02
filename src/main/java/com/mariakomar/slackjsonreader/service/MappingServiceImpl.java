package com.mariakomar.slackjsonreader.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        SlackMessageSimple slackMessage = objectMapper
                .readValue(new File(getClass().getResource("/json/beginner/111test-3.json").getFile()),
                        SlackMessageSimple.class);
        logger.info("one " + slackMessage.toString());
        return slackMessage;
    }

    public List<SlackMessageSimple> readJsonArrayWithObjectMapper() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<SlackMessageSimple> sl = objectMapper
                .readValue(new File(getClass().getResource("/json/beginner/2016-05-07.json").getFile()),
                        new TypeReference<List<SlackMessageSimple>>(){});
        logger.info("array " + sl.toString());
        return sl;
    }
}
