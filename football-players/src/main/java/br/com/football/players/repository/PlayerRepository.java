package br.com.football.players.repository;

import br.com.football.players.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByCode(final UUID code);

}
