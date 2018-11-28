package br.com.football.teams;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FailDeleteBase extends BaseTests {

  public FailDeleteBase() {
    super(shouldNotDeletePlayerWhenNotFoundFields());
  }

  private static Map<String, List<FieldDescriptor>> shouldNotDeletePlayerWhenNotFoundFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

    values.put("response_validate_shouldNotDeletePlayerWhenNotFound", FailBase.getResponseErrorDescriptors());

    return values;
  }
}
