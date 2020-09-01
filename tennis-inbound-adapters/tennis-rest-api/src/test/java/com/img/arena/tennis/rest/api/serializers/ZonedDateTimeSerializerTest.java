package com.img.arena.tennis.rest.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ZonedDateTimeSerializerTest {

    private final ZonedDateTimeSerializer zonedDateTimeSerializer = new ZonedDateTimeSerializer();

    @Before
    public void setZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/London")));
    }

    @Test
    public void shouldSerializeToGmt() throws IOException {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-10-25T10:00:00.00+00:00");
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        zonedDateTimeSerializer.serialize(zonedDateTime, jsonGenerator, null);
        verify(jsonGenerator).writeString("2020-10-25T10:00:00Z");
    }

    @Test
    public void shouldSerializeToBst() throws IOException {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-10-24T10:00:00.00+01:00");
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        zonedDateTimeSerializer.serialize(zonedDateTime, jsonGenerator, null);
        verify(jsonGenerator).writeString("2020-10-24T10:00:00+01:00");
    }

    @Test
    public void shouldSerializeWithoutNanoSeconds() throws IOException {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-10-24T10:00:00.1231+01:00");
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        zonedDateTimeSerializer.serialize(zonedDateTime, jsonGenerator, null);
        verify(jsonGenerator).writeString("2020-10-24T10:00:00+01:00");
    }
}