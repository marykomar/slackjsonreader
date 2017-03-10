package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.AttachmentSaver;
import com.mariakomar.slackjsonreader.service.FileOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Maria Komar on 09.03.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentSaverTest {
    @Autowired
    AttachmentSaver service;
    @Autowired
    FileOperations fos;

    @Test
    public void testFindAllMessagesWithAttachment() {
        service.findAllMessagesWithAttachment();
    }

    @Test
    public void testDownloadAttachments() {
        service.downloadAttachments(fos);
    }
}