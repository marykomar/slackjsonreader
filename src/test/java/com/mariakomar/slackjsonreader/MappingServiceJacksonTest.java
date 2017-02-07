package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.MappingService;
import com.mariakomar.slackjsonreader.service.MappingServiceJackson;
import org.junit.Test;

/**
 * Created by Maria Komar on 30.01.17.
 */
public class MappingServiceJacksonTest {
    @Test
    public void  testReadJsonArrayWithObjectMapper() throws Exception{
        MappingService ms = new MappingServiceJackson();
        ms.readJsonArrayWithObjectMapper();
    }
}
