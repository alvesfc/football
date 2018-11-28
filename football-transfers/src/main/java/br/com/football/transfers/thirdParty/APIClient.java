package br.com.football.transfers.thirdParty;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author alvesfc
 * @version 1.0
 */
public class APIClient {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final RestTemplate restTemplate;
    protected final String endPoint;

    public APIClient(final RestTemplate restTemplate, final String endPoint) {
        this.restTemplate = restTemplate;
        this.endPoint = endPoint;
    }

    protected ResponseEntity<JsonNode[]> get(final String uri) {
        return restTemplate.exchange(
                uri, HttpMethod.GET, new HttpEntity<>(buildHeader()),
                JsonNode[].class);
    }

    protected ResponseEntity<JsonNode> getOne(final String uri) {
        LOG.info(Markers.appendEntries(ImmutableMap.of(
                "M", "getOne",
                "uri", uri
        )), "");

        try {

            return restTemplate.exchange(
                    uri, HttpMethod.GET, new HttpEntity<>(buildHeader()),
                    JsonNode.class);
        } catch (HttpClientErrorException ex) {
            LOG.error(Markers.appendEntries(ImmutableMap.of(
                    "M", "getOne",
                    "ex", ex
            )), "");
            if(ex.getStatusCode().is4xxClientError()){

            }else if(ex.getStatusCode().is5xxServerError()){

            }
            throw ex;
            //throw new APIException(ex.getResponseBodyAsString(), ex.getStatusCode());
        }

    }

    protected <T> ResponseEntity<JsonNode> post(final String uri, final T request) {
        LOG.info(Markers.appendEntries(ImmutableMap.of(
                "M", "post",
                "uri", uri,
                "request", request.toString()
        )), "");
        try {

            return restTemplate.exchange(
                    uri, HttpMethod.POST, new HttpEntity<>(request, buildHeader()),
                    JsonNode.class);
        } catch (HttpClientErrorException ex) {
            LOG.error(Markers.appendEntries(ImmutableMap.of(
                    "M", "post",
                    "ex", ex
            )), "");
            throw ex;
           // throw new APIException(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    protected <T> ResponseEntity<JsonNode> put(final String uri, final T request) {
        LOG.info(Markers.appendEntries(ImmutableMap.of(
                "M", "put",
                "uri", uri,
                "request", request.toString()
        )), "");

        try {

            return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(request, buildHeader()), JsonNode.class);

        } catch (HttpClientErrorException ex) {
            LOG.error(Markers.appendEntries(ImmutableMap.of(
                    "M", "put",
                    "ex", ex
            )), "");
            throw ex;
           // throw new APIException(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    protected <T> ResponseEntity<JsonNode> delete(final String uri, final T request, final Map<String, String> param) {
        final HttpEntity<T> httpEntity = new HttpEntity<>(request, buildHeader());
        return restTemplate
                .exchange(uri, HttpMethod.DELETE, httpEntity, JsonNode.class, param);
    }

    private MultiValueMap<String, String> buildHeader() {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        return headers;
    }
}
