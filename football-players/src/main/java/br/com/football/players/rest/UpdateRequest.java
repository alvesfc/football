package br.com.football.players.rest;


import br.com.football.players.deserializer.LocalDateDeserializerCustom;
import br.com.football.players.exception.ErrorCode;
import br.com.football.players.validator.CollectionValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

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
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{6,60}$")
  private String fullName;
  @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
  private String nationality;
  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializerCustom.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate bornDate;
  @CollectionValue(enumClass = PositionEnum.class)
  private Set<String> positions;
}