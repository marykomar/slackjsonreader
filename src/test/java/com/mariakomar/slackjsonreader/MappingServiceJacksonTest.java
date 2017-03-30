package com.mariakomar.slackjsonreader;

import com.mariakomar.slackjsonreader.saver.MappingService;
import com.mariakomar.slackjsonreader.saver.MappingServiceJackson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
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
        File tempFolder = testFolder.newFolder("dir", "channel");
        File tempFile = new File(tempFolder, "test.txt");
        FileWriter fileWriter = new FileWriter(tempFile);
        String json = "[\n" +
                "    {\n" +
                "        \"user\": \"id1\",\n" +
                "        \"type\": \"message\",\n" +
                "        \"subtype\": \"channel_join\",\n" +
                "        \"text\": \"<user> has joined the channel\",\n" +
                "        \"ts\": \"1463495007.000002\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"user\": \"id2\",\n" +
                "        \"type\": \"message\",\n" +
                "        \"subtype\": \"channel_purpose\",\n" +
                "        \"text\": \"<user> has joined the channel\",\n" +
                "        \"ts\": \"1463495007.000003\"\n" +
                "    }\n" +
                "]";
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
        ms.setMainDirectoryPath(tempFolder.getParentFile().getAbsolutePath());
    }

    @Test
    public void testReadJsonArrayWithObjectMapper() throws Exception {
        assertEquals(2, ms.readJsonArrayWithObjectMapper().size());
    }
}
