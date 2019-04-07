package br.com.football.teams.repository;


import br.com.football.teams.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * Class that extends MongoRepository for  manipulate a Team at the database.
 *
 * @author alvesfc
 * @version 1.0
 */
public interface TeamRepository extends MongoRepository<Team, String> {
}
