package br.com.football.transfers.thirdParty.players;

import br.com.football.transfers.converter.GenericConverter;
import br.com.football.transfers.thirdParty.APIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    public Mono<PlayerResponse> find(final UUID id) {
        return super.get(endPoint + "/players/" + id.toString())
                .take(1)
                .next()
                .map(genericConverter::writeValueAsString)
                .map(player -> genericConverter.readValue(player, PlayerResponse.class));
    }
}
