package br.com.football.player.skill;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CassandraDataSet(keyspace = "football_player_skill")
public abstract class SuccessBase extends BaseTests {

  public SuccessBase() {
    super(SuccessBase.shouldCreatePlayerFields());
  }

  public SuccessBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
    super(fieldDescriptors);
  }

  private static Map<String, List<FieldDescriptor>> shouldCreatePlayerFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

//        values.put("request_validate_shouldCreatePlayer", PlayerCreateDescriptors.builder()
//                .withName()
//                .withFullname()
//                .withNationality()
//                .withBornDate()
//                .withPositions()
//                .build());
//
//        values.put("response_validate_shouldCreatePlayer", PlayerCreateDescriptors.builder()
//                .withCode()
//                .build());

    return values;
  }

}
