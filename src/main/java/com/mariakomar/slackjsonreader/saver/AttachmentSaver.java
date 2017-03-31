package com.mariakomar.slackjsonreader.saver;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private MappingService mappingService;
    private FileOperations fos;

    @Autowired
    public AttachmentSaver(MappingService mappingService, FileOperations fos) {
        this.mappingService = mappingService;
        this.fos = fos;
    }

    /**
     * Find all messages with attachments.
     */
    public List<SlackMessage> findAllMessagesWithAttachment() {
        List<SlackMessage> messages = new ArrayList<>();
        try {
            for (SlackMessage message : mappingService.readJsonArrayWithObjectMapper()) {
                if (message.getFile() != null) {
                    messages.add(message);
                }
            }
            logger.info("Found messages with attachments: {}", messages.size());
        } catch (SaverException e) {
            logger.warn("Wrong path specified");
        }
        return messages;
    }

    /**
     * Download all found attachments to specified folder.
     * Folder now is /home/maria/!slack/files/
     */
    public void downloadAttachments(String path) {
        for (SlackMessage message : findAllMessagesWithAttachment()) {
            String name = message.getFile().getName();
            String ts = message.getFile().getTimestamp();
            String url = message.getFile().getUrl_private_download();
            if (url == null) {
                url = message.getFile().getUrl_private();
            }
            if (url != null) {
                fos.downloadAndSaveWithNIO(url, path + ts + name);
                logger.info("Attachments downloaded to {}", path);
            } else {
                logger.warn("Invalid attachment {}", message.getFile());
            }
        }

    }

}
