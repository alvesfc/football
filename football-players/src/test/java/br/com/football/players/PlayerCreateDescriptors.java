package br.com.football.players;


import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayerCreateDescriptors {

    private List<FieldDescriptor> descriptors = new ArrayList<>();

    private PlayerCreateDescriptors() {

    }

    public static PlayerCreateDescriptors builder() {
        return new PlayerCreateDescriptors();
    }

    public List<FieldDescriptor> build() {
        return this.descriptors;
    }

    public PlayerCreateDescriptors withName() {
        descriptors.add(PayloadDocumentation.fieldWithPath("name")
                .description("The player's name")
                .type("Text"));
        return this;
    }

    public PlayerCreateDescriptors withFullname() {
        descriptors.add(PayloadDocumentation.fieldWithPath("fullName")
                .description("The player's fullName")
                .type("Text"));
        return this;
    }

    public PlayerCreateDescriptors withNationality() {
        descriptors.add(PayloadDocumentation.fieldWithPath("nationality")
                .description("The player's name")
                .type("Text"));
        return this;
    }

    public PlayerCreateDescriptors withBornDate() {
        descriptors.add(PayloadDocumentation.fieldWithPath("bornDate")
                .description("The player's bornDate")
                .type("Date"));
        return this;
    }

    public PlayerCreateDescriptors withActive() {
        descriptors.add(PayloadDocumentation.fieldWithPath("active")
                .description("The player's active")
                .type("Boolean"));
        return this;
    }

    public PlayerCreateDescriptors withPositions() {
        descriptors.add(PayloadDocumentation.fieldWithPath("positions")
                .description("The player's positions")
                .type("List"));
        return this;
    }

    public PlayerCreateDescriptors withCode() {
        descriptors.add(PayloadDocumentation.fieldWithPath("code")
                .description("The player's code")
                .type("Number"));
        return this;
    }

}
