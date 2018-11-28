package br.com.football.player.skill;

import org.cassandraunit.spring.CassandraDataSet;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CassandraDataSet(value = { "read.cql" }, keyspace = "football_player_skill")
public abstract class SuccessReadBase extends BaseTests {

    public SuccessReadBase() {
        super(SuccessReadBase.shouldReadPlayerFields());
    }

    public SuccessReadBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> shouldReadPlayerFields() {

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
