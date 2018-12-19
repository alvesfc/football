package br.com.football.players.rest;


import br.com.football.players.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/players")
public class PlayerResource {


    private final PlayerService playerService;

    @Autowired
    public PlayerResource(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity create(@Valid @RequestBody final CreateRequest createRequest) {


        CreateResponse createResponse = CreateResponse.builder()
                .code(playerService.create(createRequest))
                .build();

        return ResponseEntity
                .created(URI.create("/players/" + createResponse.getCode()))
                .body(createResponse);

    }

    @GetMapping(value = "/{code}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity read(@PathVariable(value = "code") final UUID code) {
        return ResponseEntity
                .ok()
                .body(playerService.find(code));
    }

    @PutMapping(value = "/{code}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity update(@PathVariable(value = "code") final UUID code,
                          @Valid @RequestBody final UpdateRequest updateRequest) {

        playerService.update(code, updateRequest);

        return ResponseEntity.ok()
                .build();

    }

    @DeleteMapping(value = "/{code}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity delete(@PathVariable(value = "code") final UUID code) {

        playerService.delete(code);

        return ResponseEntity
                .ok().build();

    }

}
