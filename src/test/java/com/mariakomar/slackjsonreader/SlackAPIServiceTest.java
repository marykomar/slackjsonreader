package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.saver.SaverException;
import com.mariakomar.slackjsonreader.saver.SlackAPIService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test for SlackAPIService
 *
 * Created by Maria Komar on 11.02.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackAPIServiceTest {

    @Autowired
    private SlackAPIService slackAPIService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAvatarAndName() throws SaverException {
        SlackUser user = new SlackUser("U4MDBM1B7");
        assertEquals("maria.komar", slackAPIService.getAvatarAndName(user).getUsername());
    }

    @Test
    public void testGetAvatarAndNameNoUser() throws SaverException {
        exception.expect(SaverException.class);
        exception.expectMessage("User not found");
        SlackUser user = new SlackUser("notExist");
        slackAPIService.getAvatarAndName(user).getUsername();
    }


    @Test
    public void testGetUserAsString() {
        assertTrue(slackAPIService.getUserAsString("U4MDBM1B7").contains("U4MDBM1B7"));
    }

}
