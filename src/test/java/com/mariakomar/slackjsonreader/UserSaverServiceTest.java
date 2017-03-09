package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.FileOperationsService;
import com.mariakomar.slackjsonreader.service.UserSaverService;
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
public class UserSaverServiceTest {
    @Autowired
    UserSaverService userSaverService;
    @Autowired
    FileOperationsService fos;

    @Test
    public void testGetAllUsersId() {
        userSaverService.getAllUsersId();
    }

    @Test
    public void testGetUsersInfoFromSlack() {
        userSaverService.getUsersInfoFromSlack();
    }

    @Test
    public void testSaveUsersJsonToFile() {
        userSaverService.saveUsersJsonToFile();
    }

    @Test
    public void testGetUsersFromFile() {
        userSaverService.getUsersFromFile();
    }

    @Test
    public void testSaveAvatars() {
        userSaverService.saveAvatars(fos);
    }
}
