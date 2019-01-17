package br.com.football.transfers.service;

import br.com.football.transfers.rest.CreateRequest;
import br.com.football.transfers.thirdParty.players.PlayerClient;
import br.com.football.transfers.thirdParty.teams.TeamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TransferService {

    static final Logger LOG = LoggerFactory.getLogger(TransferService.class);

    private TeamClient teamClient;

    private PlayerClient playerClient;

    @Autowired
    public TransferService(TeamClient teamClient, PlayerClient playerClient) {
        this.teamClient = teamClient;
        this.playerClient = playerClient;
    }

    public Mono<UUID> create(final CreateRequest createRequest) {

        return teamClient.find(createRequest.getTeam())
                .zipWith(playerClient.find(createRequest.getPlayer()))
                .map(mono -> UUID.randomUUID());
    }

    public Mono<CreateRequest> find(final UUID code) {
        return Mono.just(CreateRequest
                .builder()
                .player(UUID.randomUUID())
                .team(UUID.randomUUID())
                .value(2000000000L)
                .wage(30000000L)
                .contractUntil(2020)
                .build());
    }
}
