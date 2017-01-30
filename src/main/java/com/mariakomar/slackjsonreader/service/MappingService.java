package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackMessageSimple;

import java.io.IOException;

/**
 * Created by Maria Komar on 30.01.17.
 */
public interface MappingService {
    public SlackMessageSimple readJsonWithObjectMapper() throws IOException;
}
