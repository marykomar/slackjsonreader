package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackMessage;
import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.saver.FileOperations;
import com.mariakomar.slackjsonreader.saver.MappingService;
import com.mariakomar.slackjsonreader.saver.SlackAPIService;
import com.mariakomar.slackjsonreader.saver.UserSaver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testSaveUsersJsonToFile() {
        StringWriter content = new StringWriter();
        PrintWriter out = new PrintWriter(content);
        userSaver.saveUsersJsonToFile(out);
        out.close();
        assertEquals("[\nproper user]\n", content.toString());
    }

    @Test
    public void testGetUsersFromFile() {
        String usersInfo = "[\n" +
                "{\n" +
                "    \"ok\": true,\n" +
                "    \"user\": {\n" +
                "        \"id\": \"U2JEC1VTI\",\n" +
                "        \"name\": \"odabc\",\n" +
                "        \"profile\": {\n" +
                "            \"first_name\": \"Name\",\n" +
                "            \"last_name\": \"Last\",\n" +
                "            \"image_72\": \"https:\\/\\/secure.gravatar.com\"\n" +
                "        },\n" +
                "        \"is_admin\": false,\n" +
                "        \"is_bot\": false\n" +
                "    }\n" +
                "}\n]";
        InputStream inputStream = new ByteArrayInputStream(usersInfo.getBytes(StandardCharsets.UTF_8));
        assertEquals(1, userSaver.getUsersFromFile(inputStream).size());
    }

    @Test
    public void testSaveAvatars() {
        SlackUser user = new SlackUser("id");
        user.setAvatar("avatarURL");
        List<SlackUser> users = new ArrayList<>();
        users.add(user);
        userSaver.saveAvatars("/testPath/", users);
        verify(fos, times(1))
                .downloadAndSaveWithNIO("avatarURL", "/testPath/id");
    }
}
