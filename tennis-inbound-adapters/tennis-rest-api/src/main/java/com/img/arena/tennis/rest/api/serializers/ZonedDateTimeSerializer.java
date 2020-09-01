package com.img.arena.tennis.rest.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME_WITHOUT_NANOS = new DateTimeFormatterBuilder().parseCaseInsensitive()
                                                                                                              .append(ISO_LOCAL_DATE)
                                                                                                              .appendLiteral('T').appendValue(HOUR_OF_DAY, 2)
                                                                                                              .appendLiteral(':')
                                                                                                              .appendValue(MINUTE_OF_HOUR, 2)
                                                                                                              .optionalStart()
                                                                                                              .appendLiteral(':')
                                                                                                              .appendValue(SECOND_OF_MINUTE, 2)
                                                                                                              .parseLenient()
                                                                                                              .appendOffsetId()
                                                                                                              .parseStrict()
                                                                                                              .toFormatter();


    @Override
    public void serialize(ZonedDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(zonedDateTime.format(ISO_OFFSET_DATE_TIME_WITHOUT_NANOS));
    }
}
