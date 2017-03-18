package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackFile;
import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.saver.AttachmentSaver;
import com.mariakomar.slackjsonreader.saver.FileOperations;
import com.mariakomar.slackjsonreader.saver.MappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests for AttachmentSaver.
 *
 * Created by Maria Komar on 09.03.17.
 */
@SpringBootTest
public class AttachmentSaverTest {
    private AttachmentSaver service = null;
    private FileOperations fileOperations = null;
    private MappingService mappingService = null;

    @Before
    public void beforeEachTest() {
        mappingService = Mockito.mock(MappingService.class);
        List<List<SlackMessage>> messagesList = new ArrayList<>();
        List<SlackMessage> messages = new ArrayList<>();
        SlackMessage noFile = new SlackMessage();
        SlackMessage withFile = new SlackMessage();
        SlackFile slackFile = new SlackFile();
        slackFile.setUrl_private_download("testURL");
        slackFile.setName("name");
        slackFile.setTimestamp("now");
        withFile.setFile(slackFile);
        messages.add(noFile);
        messages.add(withFile);
        messagesList.add(messages);
        when(mappingService.readJsonArrayWithObjectMapper()).thenReturn(messagesList);
        fileOperations = Mockito.mock(FileOperations.class);
        service = new AttachmentSaver(mappingService, fileOperations);
    }

    @Test
    public void testFindAllMessagesWithAttachment() {
        assertEquals(1, service.findAllMessagesWithAttachment().size());
    }

    @Test
    public void testDownloadAttachments() {
        service.downloadAttachments("/testPath/");
        verify(fileOperations, times(1))
                .downloadAndSaveWithNIO("testURL", "/testPath/nowname");
    }
}
