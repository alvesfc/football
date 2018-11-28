package br.com.football.players;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Sql(value = "classpath:shouldUpdatePlayer.sql")
public abstract class SuccessUpdateBase extends SuccessBase {

    public SuccessUpdateBase() {
        super(shouldUpdatePlayerFields());
    }

    private static Map<String, List<FieldDescriptor>> shouldUpdatePlayerFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("request_validate_shouldUpdatePlayer", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        return values;
    }
}
