package com.mariakomar.slackjsonreader.saver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * File operations.
 *
 * Created by Maria Komar on 09.03.17.
 */
@Service
public class FileOperations {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * Download file from URL and save it to filesystem.
     *
     * @param url  link to file
     * @param file link to directory where file should be saved
     * @throws IOException when file cannot be processed
     */
    public void downloadAndSaveWithNIO(String url, String file) {
        URL u;
        try {
            u = new URL(url);
            try (ReadableByteChannel byteChannel = Channels.newChannel(u.openStream())) {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
                }
            } catch (IOException e) {
                logger.warn("File not downloaded {}", file);
            }
        } catch (MalformedURLException e) {
            logger.warn("Wrong url {}", url);
        }


    }
}
