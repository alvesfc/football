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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Class that represents a create team request .
 *
 * @author alvesfc
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRequest {

  @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
  private String name;
  @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,60}$")
  private String fullName;
  @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{2,3}$")
  private String acronym;
  @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
  private String country;

}
