package br.com.football.teams;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SuccessReadBase extends SuccessBase {

  public SuccessReadBase() {
    super(shouldReadPlayerFields());
  }

  @Before
  public void setup() {
    super.setup();
    mongoTemplate.createCollection("teams");
    MongoCollection<Document> teamsCollection = mongoTemplate.getCollection("teams");

    Document team = new Document();
    team.put("_id", "3b241101-e2bb-4255-8caf-4136c566a962");
    team.put("name", "São Paulo");
    team.put("fullName", "São Paulo Futebol Clube");
    team.put("acronym", "SPO");
    team.put("country", "Brazil");
    team.put("active", true);


    teamsCollection.insertOne(team);
  }

  private static Map<String, List<FieldDescriptor>> shouldReadPlayerFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

    values.put("response_validate_shouldReadTeam", TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withAcronym()
            .withCountry()
            .withActive()
            .build());

    return values;
  }
}
