package com.mariakomar.slackjsonreader.service;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AttachmentSaver is the class for finding attachments in messages and saving them.
 *
 * Created by Maria Komar on 09.03.17.
 */
@Service
public class AttachmentSaver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<SlackMessage> messages = new ArrayList<>();
    @Autowired
    private MappingService mappingService;
    @Autowired
    private FileOperations fos;

    /**
     * Find all messages with attachments.
     */
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
            logger.warn("Messages have not been read from files", e);
        }
        logger.info("Found messages with attachments: {}", messages.size());
    }

    /**
     * Download all found attachments to specified folder.
     */
    public void downloadAttachments() {
        String path = "/home/maria/!slack/files/";
        for (SlackMessage message : messages) {
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
                fos.downloadAndSaveWithNIO(url, path + ts + name);
            } catch (IOException e) {
                logger.warn("File not created " + name + " url " + url
                        + " other url " + message.getFile().getUrl_private()
                        + " filetype " + message.getFile().getFiletype());
            }
        }
        logger.info("Attachments downloaded to {}", path);
    }

}
