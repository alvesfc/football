package br.com.football.transfers.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

/**
 * @author peo_mcunha
 * @version 1.0
 */
@Component
public class GenericConverter {

    private final ObjectMapper objectMapper;
    private static final String STRING_DEFAULT = "";
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GenericConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String writeValueAsString(final Object value) {
        return Optional.ofNullable(value).map(content -> {
                    try {
                        return objectMapper.writeValueAsString(content);
                    } catch (JsonProcessingException e) {
                        LOG.error(Markers.appendEntries(ImmutableMap.of(
                                "method", "writeValueAsString",
                                "value", value
                        )), "",e);
                        return STRING_DEFAULT;
                    }
                }
        ).orElse(STRING_DEFAULT);
    }

    public <T> T readValue(final String value, final Class<T> clazz) {
        if (value == null) {
            return instanceDefaultReturn(clazz);
        }

        try {
            return objectMapper.readValue(value, clazz);
        } catch (final IOException e) {
            LOG.error(Markers.appendEntries(ImmutableMap.of(
                    "method", "readValue",
                    "value", value,
                    "clazz",clazz
            )), "",e);
        }

        return instanceDefaultReturn(clazz);
    }

    public JsonNode valueToTree(final Object value) {
        return objectMapper.valueToTree(value);
    }

    public ObjectReader readerForUpdating(final ObjectNode nodeDestination) {
        if (nodeDestination == null) {
            return instanceDefaultReturn(ObjectReader.class);
        }

        return objectMapper.readerForUpdating(nodeDestination);
    }

    private <T> T instanceDefaultReturn(final Class<T> clazz) {
        try {
            if (clazz.isArray()) {
                return objectMapper.readValue("[{}]", clazz);
            }

            return objectMapper.readValue("{}", clazz);
        } catch (final Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}

