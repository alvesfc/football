package br.com.football.transfers.thirdParty.teams;

import br.com.football.transfers.converter.GenericConverter;
import br.com.football.transfers.thirdParty.APIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TeamClient extends APIClient {

    private final GenericConverter genericConverter;

    @Autowired
    public TeamClient(final WebClient webClient, @Value("${endpoint.teams}") String endPoint,
            final GenericConverter genericConverter) {
        super(webClient, endPoint);
        this.genericConverter = genericConverter;
    }

    public Mono<TeamResponse> find(final UUID id) {
        return super.get(endPoint + "/teams/" + id.toString())
                .take(1)
                .next()
                .map(genericConverter::writeValueAsString)
                .map(s -> genericConverter.readValue(s, TeamResponse.class));
    }
}
