package com.mariakomar.slackjsonreader.saver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Custom Jackson deserializer enabling deserializing to LocalDateTime.
 *
 * Created by Maria Komar on 02.02.17.
 */
public class CustomDateDeserializer extends JsonDeserializer<LocalDateTime> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String timestamp = jsonParser.getText().trim();
        String[] output = timestamp.split("\\.");
        Long epochsecond = Long.valueOf(output[0]);
        int nanoOfSecond = Integer.valueOf(output[1]);
        ZoneOffset zoneOffset = ZoneOffset.of("+02:00");
        try{
            return LocalDateTime.ofEpochSecond(epochsecond, nanoOfSecond, zoneOffset);
        } catch (NumberFormatException e) {
            logger.warn("Unable to deserialize timestamp " + timestamp, e);
        }
        return null;
    }
}
