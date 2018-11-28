package br.com.football.teams;

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

        values.put("request_validate_shouldNotCreateTeamWithInvalidBornDate", TeamCreateDescriptors.builder()
                .withName()
                .withAcronym()
                .withCountry()
                .build());

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
