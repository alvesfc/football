package br.com.football.transfers.rest;

import br.com.football.transfers.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/transfers")
public class TransfersResource {

    private TransferService transferService;

    @Autowired
    public TransfersResource(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Mono<ResponseEntity> create(@Valid @RequestBody final CreateRequest createRequest) {

        return transferService.create(createRequest)
                .map(id -> ResponseEntity
                        .created(URI.create("/transfers/" + id))
                        .body(CreateResponse.builder().id(id).build()));
    }

    @GetMapping(value = "/{code}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Mono<ResponseEntity> read(@PathVariable(value = "code") final UUID code) {

        return transferService.find(code)
                .map(readResponse -> ResponseEntity
                        .ok()
                        .body(readResponse));
    }

}
