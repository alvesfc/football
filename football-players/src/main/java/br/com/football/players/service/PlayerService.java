package br.com.football.players.service;

import br.com.football.players.domain.Player;
import br.com.football.players.domain.Position;
import br.com.football.players.exception.ErrorCode;
import br.com.football.players.repository.PlayerRepository;
import br.com.football.players.rest.CreateRequest;
import br.com.football.players.rest.PositionEnum;
import br.com.football.players.rest.ReadResponse;
import br.com.football.players.rest.UpdateRequest;
import br.org.ehandler.exception.NotFoundException;
import br.org.ehandler.exception.message.MessageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlayerService {

  private PlayerRepository playerRepository;

  @Autowired
  public PlayerService(final PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  private Player getEntity(Long code) {
    return playerRepository.findById(code)
            .orElseThrow(() -> new NotFoundException(new MessageDefault(ErrorCode.PLAYER_NOT_FOUND)));
  }

  public Long create(final CreateRequest request) {

    Player player = playerRepository.save(Player.builder()
            .name(request.getName())
            .fullName(request.getFullName())
            .nationality(request.getNationality())
            .bornDate(request.getBornDate())
            .active(Boolean.TRUE)
            .positions(request.getPositions()
                    .stream()
                    .map(position -> Position.builder()
                            .id(PositionEnum.convert(position).getCode())
                            .build())
                    .collect(Collectors.toSet()))
            .build());

    return player.getId();

  }

  public void update(final Long code, final UpdateRequest request) {

    Player entity = getEntity(code);

    Player update = Player.builder()
            .id(code)
            .name(request.getName() == null ? entity.getName() : request.getName())
            .fullName(request.getFullName() == null ? entity.getFullName() : request.getFullName())
            .nationality(request.getNationality() == null ? entity.getNationality() : request.getNationality())
            .bornDate(request.getBornDate() == null ? entity.getBornDate() : request.getBornDate())
            .active(entity.getActive())
            .positions(request.getPositions() == null ? entity.getPositions() : request.getPositions()
                    .stream()
                    .map(position -> Position.builder()
                            .id(PositionEnum.convert(position).getCode())
                            .build())
                    .collect(Collectors.toSet()))
            .build();

    playerRepository.save(update);
  }

  public void delete(final Long code) {
    Player entity = getEntity(code);

    playerRepository.delete(entity);

  }

  public ReadResponse find(final Long code) {
    Player entity = getEntity(code);

    return ReadResponse.builder()
            .name(entity.getName())
            .fullName(entity.getFullName())
            .nationality(entity.getNationality())
            .bornDate(entity.getBornDate())
            .active(entity.getActive())
            .positions(entity.getPositions()
                    .stream()
                    .map(p -> PositionEnum.convert(p.getAcronym()))
                    .collect(Collectors.toSet()))
            .build();
  }


}
