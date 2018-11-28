package br.com.football.transfers.rest;

import br.com.football.transfers.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody
    ResponseEntity create(@Valid @RequestBody final CreateRequest createRequest) {

        UUID uuid = transferService.create(createRequest);

        return ResponseEntity
                .created(URI.create("/transfers/" + uuid))
                .body(CreateResponse.builder().id(uuid).build());
    }

}
