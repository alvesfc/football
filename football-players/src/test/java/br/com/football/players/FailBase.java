package br.com.football.players;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FailBase extends BaseTests {


    public FailBase() {
        super(invalidFields());
    }

    public FailBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> invalidFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        values.put("request_validate_shouldNotCreatePlayerWithInvalidBornDate", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithInvalidBornDate", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotCreatePlayerWithInvalidFullname", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithInvalidFullname", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotCreatePlayerWithInvalidName", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithInvalidName", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotCreatePlayerWithInvalidNationality", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithInvalidNationality", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotCreatePlayerWithInvalidPositions", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithInvalidPositions", getResponseErrorDescriptors());


        values.put("request_validate_shouldNotCreatePlayerWithoutName", PlayerCreateDescriptors.builder()
                .withFullname()
                .withNationality()
                .withBornDate()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithoutName", getResponseErrorDescriptors());

        values.put("request_validate_shouldNotCreatePlayerWithoutNationality", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withBornDate()
                .withPositions()
                .build());
        values.put("response_validate_shouldNotCreatePlayerWithoutNationality", getResponseErrorDescriptors());

        values.put("request_validate_shouldNotCreatePlayerWithoutBornDate", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withPositions()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithoutBornDate", getResponseErrorDescriptors());

        values.put("request_validate_shouldNotCreatePlayerWithoutPositions", PlayerCreateDescriptors.builder()
                .withName()
                .withFullname()
                .withNationality()
                .withBornDate()
                .build());

        values.put("response_validate_shouldNotCreatePlayerWithoutPositions", getResponseErrorDescriptors());

        return values;
    }

    protected static List<FieldDescriptor> getResponseErrorDescriptors() {
        List<FieldDescriptor> descriptors = new ArrayList<>();

        FieldDescriptor info = PayloadDocumentation.fieldWithPath("errors")
                .description("Object with errors messagens")
                .type("String");

        FieldDescriptor code = PayloadDocumentation.fieldWithPath("errors[].code")
                .description("The error's code")
                .type("String");

        FieldDescriptor message = PayloadDocumentation.fieldWithPath("errors[].message")
                .description("The error's message")
                .type("String");

        descriptors.add(code);
        descriptors.add(info);
        descriptors.add(message);
        return descriptors;
    }
}
