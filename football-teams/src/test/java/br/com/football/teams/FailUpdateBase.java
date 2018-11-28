package br.com.football.teams;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FailUpdateBase extends BaseTests {

  public FailUpdateBase() {
    super(invalidFields());
  }

  private static Map<String, List<FieldDescriptor>> invalidFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();


    return values;
  }
}
