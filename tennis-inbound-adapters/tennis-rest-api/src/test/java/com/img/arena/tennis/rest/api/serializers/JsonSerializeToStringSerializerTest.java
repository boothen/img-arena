package com.img.arena.tennis.rest.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.img.arena.tennis.datastore.model.MatchId;

import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JsonSerializeToStringSerializerTest {

    private final JsonSerializeToStringSerializer jsonSerializeToStringSerializer = new JsonSerializeToStringSerializer();

    @Test
    public void shouldSerializeToString() throws IOException {
        MatchId matchId = new MatchId(UUID.fromString("ff1b1322-5570-4846-a421-e14acbcc4a2b"));
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        jsonSerializeToStringSerializer.serialize(matchId, jsonGenerator, null);
        verify(jsonGenerator).writeString("ff1b1322-5570-4846-a421-e14acbcc4a2b");
    }
}