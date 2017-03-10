package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AttachmentSaver is the class for finding attachments in messages and saving them.
 *
 * Created by Maria Komar on 09.03.17.
 */
@Component
public class AttachmentSaver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MappingService mappingService;
    List<SlackMessage> messages = new ArrayList<>();

    /**
     * Create a new AttachmentSaver.
     *
     * @param mappingService
     */
    @Autowired
    public AttachmentSaver(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    public void findAllMessagesWithAttachment() {
        try {
            for (List<SlackMessage> list : mappingService.readJsonArrayWithObjectMapper()) {
                for (SlackMessage message : list) {
                    if (message.getFile() != null) {
                        messages.add(message);
                    }

                }
            }
        } catch (IOException e) {
            logger.warn("Messages have not been read");
        }
    }

    public void downloadAttachments(FileOperations fos) {
        for (SlackMessage message : messages) {
            String path = "/home/maria/!slack/files/";
            String name = message.getFile().getName();
            String ts = message.getFile().getTimestamp();
            String url = message.getFile().getUrl_private_download();
            if (url == null) {
                url = message.getFile().getUrl_private();
            }
            if (name.equals("coding-task.zip")) {
                logger.info("broken file " + url);
            }
            try {
                fos.downloadWithNIO(url, path + ts + name);
            } catch (IOException e) {
                logger.warn("File not created " + name + " url " + url
                        + " other url " + message.getFile().getUrl_private()
                        + " filetype " + message.getFile().getFiletype());
            }
        }
    }

}
