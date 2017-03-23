package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.saver.SlackAPIService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by Maria Komar on 11.02.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackAPIServiceTest {

    @Autowired
    private SlackAPIService slackAPIService;

    @Test
    public void testGetAvatarAndName() {
        SlackUser user = new SlackUser("U4MDBM1B7");
        assertEquals("maria.komar", slackAPIService.getAvatarAndName(user).getUsername());
    }

    @Test
    public void testGetUserAsString() {
        String expected = "{\n" +
                "    \"ok\": true,\n" +
                "    \"user\": {\n" +
                "        \"id\": \"U4MDBM1B7\",\n" +
                "        \"team_id\": \"T4N1BQ4VC\",\n" +
                "        \"name\": \"maria.komar\",\n" +
                "        \"deleted\": false,\n" +
                "        \"status\": null,\n" +
                "        \"color\": \"9f69e7\",\n" +
                "        \"real_name\": \"\",\n" +
                "        \"tz\": \"Europe\\/Helsinki\",\n" +
                "        \"tz_label\": \"Eastern European Time\",\n" +
                "        \"tz_offset\": 7200,\n" +
                "        \"profile\": {\n" +
                "            \"avatar_hash\": \"g6f9f042f37a\",\n" +
                "            \"real_name\": \"\",\n" +
                "            \"real_name_normalized\": \"\",\n" +
                "            \"image_24\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=24&d=https%3A%2F%2Fa.slack-edge.com%2F66f9%2Fimg%2Favatars%2Fava_0016-24.png\",\n" +
                "            \"image_32\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=32&d=https%3A%2F%2Fa.slack-edge.com%2F66f9%2Fimg%2Favatars%2Fava_0016-32.png\",\n" +
                "            \"image_48\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=48&d=https%3A%2F%2Fa.slack-edge.com%2F66f9%2Fimg%2Favatars%2Fava_0016-48.png\",\n" +
                "            \"image_72\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=72&d=https%3A%2F%2Fa.slack-edge.com%2F66f9%2Fimg%2Favatars%2Fava_0016-72.png\",\n" +
                "            \"image_192\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=192&d=https%3A%2F%2Fa.slack-edge.com%2F7fa9%2Fimg%2Favatars%2Fava_0016-192.png\",\n" +
                "            \"image_512\": \"https:\\/\\/secure.gravatar.com\\/avatar\\/6f9f042f37a4571d2090b1c9c047b9da.jpg?s=512&d=https%3A%2F%2Fa.slack-edge.com%2F7fa9%2Fimg%2Favatars%2Fava_0016-512.png\"\n" +
                "        },\n" +
                "        \"is_admin\": true,\n" +
                "        \"is_owner\": true,\n" +
                "        \"is_primary_owner\": true,\n" +
                "        \"is_restricted\": false,\n" +
                "        \"is_ultra_restricted\": false,\n" +
                "        \"is_bot\": false,\n" +
                "        \"updated\": 1490100391,\n" +
                "        \"has_2fa\": false\n" +
                "    }\n" +
                "}\n";
        assertEquals(expected, slackAPIService.getUserAsString("U4MDBM1B7"));
    }

}
