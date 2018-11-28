package br.com.football.players;

import org.flywaydb.test.annotation.FlywayTest;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FlywayTest
public abstract class SuccessBase extends BaseTests {

    public SuccessBase() {
        super(SuccessBase.shouldCreatePlayerFields());
    }

    public SuccessBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> shouldCreatePlayerFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("request_validate_shouldCreatePlayer", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldCreatePlayer", PlayerCreateDescriptors.builder()
                .withCode()
                .build());

        return values;
    }

}
