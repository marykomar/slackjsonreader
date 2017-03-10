package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.FileOperations;
import com.mariakomar.slackjsonreader.service.UserSaver;
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
public class UserSaverTest {
    @Autowired
    UserSaver userSaver;
    @Autowired
    FileOperations fos;

    @Test
    public void testGetAllUsersId() {
        userSaver.getAllUsersId();
    }

    @Test
    public void testGetUsersInfoFromSlack() {
        userSaver.getUsersInfoFromSlack();
    }

    @Test
    public void testSaveUsersJsonToFile() {
        userSaver.saveUsersJsonToFile();
    }

    @Test
    public void testGetUsersFromFile() {
        userSaver.getUsersFromFile();
    }

    @Test
    public void testSaveAvatars() {
        userSaver.saveAvatars(fos);
    }
}
