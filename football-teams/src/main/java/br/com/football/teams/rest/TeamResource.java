package br.com.football.teams.rest;


import br.com.football.teams.service.TeamService;
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
@RequestMapping("/teams")
public class TeamResource {

  private TeamService teamService;

  @Autowired
  public TeamResource(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE,
          produces = APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity create(@Valid @RequestBody final CreateRequest createRequest) {


    UUID uuid = teamService.create(createRequest);

    return ResponseEntity
            .created(URI.create("/teams/" + uuid))
            .body(CreateResponse.builder().code(uuid).build());

  }

  @GetMapping(value = "/{code}",
          consumes = APPLICATION_JSON_VALUE,
          produces = APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity read(@PathVariable(value = "code") final UUID code) {

    return ResponseEntity
            .ok()
            .body(teamService.find(code));
  }

  @PutMapping(value = "/{code}",
          consumes = APPLICATION_JSON_VALUE,
          produces = APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity update(@PathVariable(value = "code") final UUID code,
                        @Valid @RequestBody final UpdateRequest updateRequest) {

    teamService.update(code, updateRequest);

    return ResponseEntity.ok()
            .build();

  }

  @DeleteMapping(value = "/{code}",
          consumes = APPLICATION_JSON_VALUE,
          produces = APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity delete(@PathVariable(value = "code") final UUID code) {

    teamService.delete(code);
    return ResponseEntity
            .ok().build();

  }


}
