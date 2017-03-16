package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.AttachmentSaver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for AttachmentSaver.
 *
 * Created by Maria Komar on 09.03.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentSaverTest {
    @Autowired
    private AttachmentSaver service;

    @Test
    public void testFindAllMessagesWithAttachment() {
        service.findAllMessagesWithAttachment();
    }

    @Test
    public void testDownloadAttachments() {
        service.downloadAttachments("/home/maria/!slack/files/");
    }
}
