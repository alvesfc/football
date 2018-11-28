package br.com.football.players;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Sql(value = "classpath:shouldReadPlayer.sql")
public abstract class SuccessReadBase extends SuccessBase {

    public SuccessReadBase() {
        super(shouldReadPlayerFields());
    }

    private static Map<String, List<FieldDescriptor>> shouldReadPlayerFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("response_validate_shouldReadPlayer", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withActive()
                .withPositions()
                .build());

        return values;
    }
}
