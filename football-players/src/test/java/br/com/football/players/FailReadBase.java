package br.com.football.players;

import org.flywaydb.test.annotation.FlywayTest;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FlywayTest
public abstract class FailReadBase extends BaseTests {

    public FailReadBase() {
        super(shouldNotReadPlayerWhenNotFoundFields());
    }

    private static Map<String, List<FieldDescriptor>> shouldNotReadPlayerWhenNotFoundFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("response_validate_shouldNotReadPlayerWhenNotFound", FailBase.getResponseErrorDescriptors());

        return values;
    }
}
