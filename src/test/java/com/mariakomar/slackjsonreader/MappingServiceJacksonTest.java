package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.MappingService;
import com.mariakomar.slackjsonreader.service.MappingServiceJackson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Maria Komar on 30.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MappingServiceJacksonTest {

    @Autowired
    MappingService ms;

    @Test
    public void  testReadJsonArrayWithObjectMapper() throws Exception{
        ms.readJsonArrayWithObjectMapper();
    }
}
