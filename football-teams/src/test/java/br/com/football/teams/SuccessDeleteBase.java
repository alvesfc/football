package br.com.football.teams;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;

import java.util.HashMap;

public abstract class SuccessDeleteBase extends SuccessBase {

  public SuccessDeleteBase() {
    super(new HashMap<>());
  }

  @Before
  public void setup() {
    super.setup();

    mongoTemplate.createCollection("teams");
    MongoCollection<Document> teamsCollection = mongoTemplate.getCollection("teams");

    Document team = new Document();
    team.put("_id", "4614eb6f-bcc8-498c-8013-09e687e08d69");
    team.put("name", "Milan");
    team.put("fullName", "Associazione Calcio Milan");
    team.put("acronym", "MIL");
    team.put("country", "Italy");
    team.put("active", true);

    teamsCollection.insertOne(team);
  }
}
