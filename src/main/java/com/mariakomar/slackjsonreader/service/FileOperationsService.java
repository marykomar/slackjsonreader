package com.mariakomar.slackjsonreader.service;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Maria Komar on 09.03.17.
 */
@Component
public class FileOperationsService {
    public void downloadWithNIO(String url, String file) throws IOException {
        URL u = new URL(url);
        ReadableByteChannel byteChannel = Channels.newChannel(u.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        byteChannel.close();
        fos.close();
    }
}
