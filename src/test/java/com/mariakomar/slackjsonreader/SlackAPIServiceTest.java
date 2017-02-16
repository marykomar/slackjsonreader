package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.model.SlackUser;
import com.mariakomar.slackjsonreader.service.SlackAPIService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Maria Komar on 11.02.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackAPIServiceTest {

    @Autowired
    SlackAPIService slackAPIService;

    @Test
    public void testgetAvatarAndName(){
        SlackUser user = new SlackUser("U150DHUL9");
        slackAPIService.getAvatarAndName(user);
    }

}
