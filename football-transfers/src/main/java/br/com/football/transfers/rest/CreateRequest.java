package br.com.football.transfers.rest;

import br.com.football.transfers.exeptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRequest {

    @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
    private UUID team;
    @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
    private UUID player;
    @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
    private Long value;
    @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
    private Long wage;
    @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
    private Integer contractUntil;
}
