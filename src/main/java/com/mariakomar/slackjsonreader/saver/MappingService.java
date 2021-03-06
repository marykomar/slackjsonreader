package com.mariakomar.slackjsonreader.saver;

import com.mariakomar.slackjsonreader.model.SlackMessage;

import java.util.List;

/**
 * Deserializing interface
 *
 * Created by Maria Komar on 30.01.17.
 */
public interface MappingService {
    //List<List<SlackMessage>> readJsonArrayWithObjectMapper();
    List<SlackMessage> readJsonArrayWithObjectMapper() throws SaverException;

    public String getMainDirectoryPath();

    public void setMainDirectoryPath(String mainDirectoryPath);
}
