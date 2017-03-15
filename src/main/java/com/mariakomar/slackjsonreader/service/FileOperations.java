package com.mariakomar.slackjsonreader.service;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
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

    /**
     * Download file from URL and save it to filesystem.
     *
     * @param url  link to file
     * @param file link to directory where file should be saved
     * @throws IOException when file cannot be processed
     */
    public void downloadAndSaveWithNIO(String url, String file) throws IOException {
        URL u = new URL(url);
        ReadableByteChannel byteChannel = Channels.newChannel(u.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        byteChannel.close();
        fos.close();
    }
}
