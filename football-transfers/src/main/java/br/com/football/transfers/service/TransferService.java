package br.com.football.transfers.service;

import br.com.football.transfers.rest.CreateRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferService {

    public UUID create(final CreateRequest createRequest) {

        return UUID.randomUUID();
    }
}
