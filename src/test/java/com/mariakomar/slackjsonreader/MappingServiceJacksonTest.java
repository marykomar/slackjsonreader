package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.saver.MappingService;
import com.mariakomar.slackjsonreader.saver.MappingServiceJackson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for MappingServiceJackson
 *
 * Created by Maria Komar on 30.01.17.
 */
@SpringBootTest
public class MappingServiceJacksonTest {
    private MappingService ms;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void beforeEachTest() throws IOException {
        ms = new MappingServiceJackson();
        File tempFolder = testFolder.newFolder();
        String testDirectory = "/home/maria/tes";
        ms.setMainDirectoryPath(testDirectory);
    }

    @Test
    public void testReadJsonArrayWithObjectMapper() throws Exception {
        assertEquals(2, ms.readJsonArrayWithObjectMapper().size());
    }
}
