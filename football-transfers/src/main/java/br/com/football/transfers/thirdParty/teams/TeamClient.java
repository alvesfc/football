package br.com.football.transfers.thirdParty.teams;

import br.com.football.transfers.converter.GenericConverter;
import br.com.football.transfers.thirdParty.APIClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class TeamClient extends APIClient {

    private final GenericConverter genericConverter;

    @Autowired
    public TeamClient(RestTemplate restTemplate, @Value("${endpoint.teams}") String endPoint,
            final GenericConverter genericConverter) {
        super(restTemplate, endPoint);
        this.genericConverter = genericConverter;
    }

    public TeamResponse find(final UUID id) {
        final ResponseEntity<JsonNode> responseEntity = super.getOne(endPoint + "/teams/" + id.toString());
        return genericConverter.readValue(responseEntity.getBody().asText(), TeamResponse.class);
    }
}
