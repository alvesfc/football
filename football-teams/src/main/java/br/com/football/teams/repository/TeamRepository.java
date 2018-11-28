package br.com.football.teams.repository;


import br.com.football.teams.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TeamRepository extends MongoRepository<Team, String> {
}
