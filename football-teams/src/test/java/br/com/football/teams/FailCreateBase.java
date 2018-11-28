package br.com.football.teams;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FailCreateBase extends FailBase {

  public FailCreateBase() {
    super(shouldNotCreateTeam());
  }

  private static Map<String, List<FieldDescriptor>> shouldNotCreateTeam() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

    List<FieldDescriptor> invalidFields = TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withAcronym()
            .withCountry()
            .build();

    values.put("request_validate_shouldNotCreateTeamWithInvalidAcronym", invalidFields);
    values.put("request_validate_shouldNotCreateTeamWithInvalidCountry", invalidFields);
    values.put("request_validate_shouldNotCreateTeamWithInvalidFullname", invalidFields);
    values.put("request_validate_shouldNotCreateTeamWithInvalidName", invalidFields);

    values.put("response_validate_shouldNotCreateTeamWithInvalidAcronym", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithInvalidCountry", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithInvalidFullname", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithInvalidName", FailBase.getResponseErrorDescriptors());


    values.put("request_validate_shouldNotCreateTeamWithoutAcronym", TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withCountry()
            .build());

    values.put("request_validate_shouldNotCreateTeamWithoutCountry", TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withAcronym()
            .build());

    values.put("request_validate_shouldNotCreateTeamWithoutFullname", TeamCreateDescriptors.builder()
            .withName()
            .withAcronym()
            .withCountry()
            .build());

    values.put("request_validate_shouldNotCreateTeamWithoutName", TeamCreateDescriptors.builder()
            .withFullname()
            .withAcronym()
            .withCountry()
            .build());

    values.put("response_validate_shouldNotCreateTeamWithoutAcronym", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithoutCountry", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithoutFullname", FailBase.getResponseErrorDescriptors());
    values.put("response_validate_shouldNotCreateTeamWithoutName", FailBase.getResponseErrorDescriptors());


    return values;
  }
}
