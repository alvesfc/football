package br.com.football.players;

import org.flywaydb.test.annotation.FlywayTest;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.football.players.FailBase.getResponseErrorDescriptors;

@FlywayTest
public abstract class FailUpdateBase extends BaseTests {

    public FailUpdateBase() {
        super(invalidFields());
    }

    private static Map<String, List<FieldDescriptor>> invalidFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("request_validate_shouldNotUpdatePlayerWhenNotFound", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWhenNotFound", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotUpdatePlayerWithInvalidFullname", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWithInvalidFullname", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotUpdatePlayerWithInvalidName", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWithInvalidName", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotUpdatePlayerWithInvalidNationality", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWithInvalidNationality", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotUpdatePlayerWithInvalidPositions", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWithInvalidPositions", getResponseErrorDescriptors());

        values.put("request_validate_shouldNotUpdatePlayerWithInvalidBornDate", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotUpdatePlayerWithInvalidBornDate", getResponseErrorDescriptors());

        return values;
    }
}
