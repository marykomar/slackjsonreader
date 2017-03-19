package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.saver.FileOperations;
import com.mariakomar.slackjsonreader.saver.MappingService;
import com.mariakomar.slackjsonreader.saver.UserSaver;
import com.mariakomar.slackjsonreader.service.SlackAPIService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests for UserSaver.
 *
 * Created by Maria Komar on 09.03.17.
 */
@SpringBootTest
public class UserSaverTest {
    private UserSaver userSaver = null;
    private MappingService mappingService = null;
    private FileOperations fos = null;
    private SlackAPIService slackAPIService = null;

    @Before
    public void beforeEachTest() {
        mappingService = Mockito.mock(MappingService.class);
        fos = Mockito.mock(FileOperations.class);
        slackAPIService = Mockito.mock(SlackAPIService.class);
        List<List<SlackMessage>> messagesList = new ArrayList<>();
        List<SlackMessage> messages = new ArrayList<>();
        SlackMessage noUser = new SlackMessage();
        SlackMessage withUser = new SlackMessage();
        SlackUser user = new SlackUser("id");
        withUser.setUser(user);
        messages.add(noUser);
        messages.add(withUser);
        messagesList.add(messages);
        when(mappingService.readJsonArrayWithObjectMapper()).thenReturn(messagesList);
        when(slackAPIService.getUserAsString("id")).thenReturn("proper user");
        userSaver = new UserSaver(mappingService, slackAPIService, fos);
    }

    @Test
    public void testGetAllUsersId() {
        assertEquals(1, userSaver.getAllUsersId().size());
    }

    @Test
    public void testGetUsersInfoFromSlack() {
        String found = "proper user";
        List<String> foundUsers = new ArrayList<>();
        foundUsers.add(found);
        assertEquals(foundUsers, userSaver.getUsersInfoFromSlack());
    }

    // TODO fix test
    @Test
    public void testSaveUsersJsonToFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(baos);
        userSaver.saveUsersJsonToFile(out);
        String result = new String(baos.toByteArray());
        assertEquals("1", result);
    }

    @Test
    public void testGetUsersFromFile() {
        userSaver.getUsersFromFile();
    }

    @Test
    public void testSaveAvatars() {
        userSaver.saveAvatars();
    }
}
