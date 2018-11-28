package br.com.football.teams.rest;

import br.com.football.teams.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateRequest {

  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
  private String name;
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,60}$")
  private String fullName;
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{2,3}$")
  private String acronym;
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
  private String country;

}
