package br.com.football.transfers.thirdParty;

import br.com.football.transfers.FootballTransfersApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * @author alvesfc
 * @version 1.0
 */
public class APIClient {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final WebClient webClient;
    protected final String endPoint;

    public APIClient(final WebClient webClient, final String endPoint) {
        this.webClient = webClient;
        this.endPoint = endPoint;
    }

    protected Flux<JsonNode> get(final String uri) {
        LOG.info(Markers.appendEntries(ImmutableMap.of(
                "M", "get",
                "uri", uri
        )), "");

        return webClient.get()
                .uri(uri)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, getClientResponseMonoFunction())
                .bodyToFlux(JsonNode.class);
    }

    private Function<ClientResponse, Mono<? extends Throwable>> getClientResponseMonoFunction() {
        return response ->

                response.body(BodyExtractors.toDataBuffers())
                        .reduce(DataBuffer::write)
                        .map(this::buildBytes)
                        .defaultIfEmpty(new byte[0])
                        .flatMap(bodyBytes -> {
                            return buildException(response, bodyBytes);
                        });

    }

    private byte[] buildBytes(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        return bytes;
    }

    private Mono<? extends Throwable> buildException(ClientResponse response, byte[] bodyBytes) {
        return Mono.error(new WebClientResponseException(buildErrorMessage(response),
                response.statusCode().value(),
                response.statusCode().getReasonPhrase(),
                response.headers().asHttpHeaders(),
                bodyBytes,
                buildCharset(response)
        ));
    }

    private Charset buildCharset(ClientResponse response) {
        return response.headers().contentType()
                .map(MimeType::getCharset)
                .orElse(StandardCharsets.ISO_8859_1);
    }

    private String buildErrorMessage(ClientResponse response) {
        return String.format("ClientResponse has erroneous status code: %d %s",
                response.statusCode().value(),
                response.statusCode().getReasonPhrase());
    }
}
