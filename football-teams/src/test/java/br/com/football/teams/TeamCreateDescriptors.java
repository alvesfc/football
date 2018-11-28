package br.com.football.teams;


import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;

import java.util.ArrayList;
import java.util.List;


public class TeamCreateDescriptors {

  private List<FieldDescriptor> descriptors = new ArrayList<>();

  private TeamCreateDescriptors() {

  }

  public static TeamCreateDescriptors builder() {
    return new TeamCreateDescriptors();
  }

  public List<FieldDescriptor> build() {
    return this.descriptors;
  }

  private FieldDescriptor builDescriptor(String name, String description, String type, Boolean optional) {
    FieldDescriptor descriptor = PayloadDocumentation.fieldWithPath(name)
            .description(description)
            .type(type);

    if (optional) {
      return descriptor.optional();
    }
    return descriptor;
  }

  public TeamCreateDescriptors withName() {
    descriptors.add(builDescriptor("name","The team's name","Text",false));
    return this;
  }

  public TeamCreateDescriptors withFullname() {
    descriptors.add(PayloadDocumentation.fieldWithPath("fullName")
            .description("The team's fullName")
            .type("Text"));
    return this;
  }

  public TeamCreateDescriptors withAcronym() {
    descriptors.add(PayloadDocumentation.fieldWithPath("acronym")
            .description("The team's acronym")
            .type("Text"));
    return this;
  }

  public TeamCreateDescriptors withCountry() {
    descriptors.add(PayloadDocumentation.fieldWithPath("country")
            .description("The team's country")
            .type("Text"));
    return this;
  }

  public TeamCreateDescriptors withActive() {
    descriptors.add(PayloadDocumentation.fieldWithPath("active")
            .description("The team's active")
            .type("Boolean"));
    return this;
  }

  public TeamCreateDescriptors withCode() {
    descriptors.add(PayloadDocumentation.fieldWithPath("code")
            .description("The team's code")
            .type("Number"));
    return this;
  }

}
