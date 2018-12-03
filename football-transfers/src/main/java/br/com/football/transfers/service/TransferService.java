package br.com.football.transfers.service;

import br.com.football.transfers.rest.CreateRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TransferService {

    public Mono<UUID> create(final CreateRequest createRequest) {

        return Mono.just(UUID.randomUUID());
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
