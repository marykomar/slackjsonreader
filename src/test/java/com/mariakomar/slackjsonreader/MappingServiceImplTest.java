package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.service.MappingServiceImpl;
import org.junit.Test;

/**
 * Created by Maria Komar on 30.01.17.
 */
public class MappingServiceImplTest {
    @Test
    public void testReadJsonWithObjectMapper() throws Exception{
        MappingServiceImpl ms = new MappingServiceImpl();
        ms.readJsonWithObjectMapper();
    }

    @Test
    public void  testReadJsonArrayWithObjectMapper() throws Exception{
        MappingServiceImpl ms = new MappingServiceImpl();
        ms.readJsonArrayWithObjectMapper();
    }
}
