package br.com.football.transfers.thirdParty.players;

import br.com.football.transfers.converter.GenericConverter;
import br.com.football.transfers.thirdParty.APIClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PlayerClient extends APIClient {

    private final GenericConverter genericConverter;

    @Autowired
    public PlayerClient(final WebClient webClient, @Value("${endpoint.players}") String endPoint,
            final GenericConverter genericConverter) {
        super(webClient, endPoint);
        this.genericConverter = genericConverter;
    }

    public PlayerResponse find(final UUID id) {
       // final Mono<JsonNode> jsonNode = super.get(endPoint + "/players/" + id.toString());
        //return genericConverter.readValue(responseEntity.getBody().asText(), PlayerResponse.class);
        return null;
    }
}
