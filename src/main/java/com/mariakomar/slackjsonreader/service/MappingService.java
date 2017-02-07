package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackMessageSimple;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maria Komar on 30.01.17.
 */
public interface MappingService {
    public void readJsonArrayWithObjectMapper() throws IOException;
}
