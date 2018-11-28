package br.com.football.teams.service;

import br.com.football.teams.domain.Team;
import br.com.football.teams.exception.ErrorCode;
import br.com.football.teams.repository.TeamRepository;
import br.com.football.teams.rest.CreateRequest;
import br.com.football.teams.rest.ReadResponse;
import br.com.football.teams.rest.UpdateRequest;
import br.org.ehandler.exception.NotFoundException;
import br.org.ehandler.exception.message.MessageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamService {

  private TeamRepository teamRepository;

  @Autowired
  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  private Team getEntity(final UUID code) {
    return teamRepository.findById(code.toString())
            .orElseThrow(() -> new NotFoundException(new MessageDefault(ErrorCode.TEAM_NOT_FOUND)));
  }

  public UUID create(final CreateRequest createRequest) {

    return UUID.fromString(teamRepository.save(Team.builder()
            .code(UUID.randomUUID().toString())
            .acronym(createRequest.getAcronym())
            .fullName(createRequest.getFullName())
            .name(createRequest.getName())
            .country(createRequest.getCountry())
            .active(Boolean.TRUE)
            .build()).getCode());
  }

  public ReadResponse find(final UUID code) {
    Team team = getEntity(code);

    return ReadResponse.builder()
            .name(team.getName())
            .fullName(team.getFullName())
            .acronym(team.getAcronym())
            .country(team.getCountry())
            .active(team.getActive())
            .build();
  }

  public void update(final UUID code, final UpdateRequest updateRequest) {
    Team team = getEntity(code);

    teamRepository.save(Team.builder()
            .code(team.getCode())
            .acronym(updateRequest.getAcronym())
            .fullName(updateRequest.getFullName())
            .name(updateRequest.getName())
            .country(updateRequest.getCountry())
            .active(team.getActive())
            .build());
  }

  public void delete(final UUID code) {
    teamRepository.save(Team.builder()
            .code(getEntity(code).getCode())
            .active(Boolean.FALSE)
            .build());
  }
}
