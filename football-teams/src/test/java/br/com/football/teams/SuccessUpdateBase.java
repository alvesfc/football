package br.com.football.teams;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SuccessUpdateBase extends SuccessBase {

  public SuccessUpdateBase() {
    super(shouldUpdatePlayerFields());
  }

  @Before
  public void setup(){
    super.setup();
    mongoTemplate.createCollection("teams");
    MongoCollection<Document> teamsCollection = mongoTemplate.getCollection("teams");

    Document team = new Document();
    team.put("_id", "e62f1384-f2f0-4817-a0b8-49a24456a532");
    team.put("name", "Bayern München");
    team.put("fullName", "Fußball-Club Bayern München");
    team.put("acronym", "BAY");
    team.put("country", "Germany");
    team.put("active", true);


    teamsCollection.insertOne(team);
  }

  private static Map<String, List<FieldDescriptor>> shouldUpdatePlayerFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

    values.put("request_validate_shouldUpdateTeam", TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withAcronym()
            .withCountry()
            .build());

    return values;
  }
}
