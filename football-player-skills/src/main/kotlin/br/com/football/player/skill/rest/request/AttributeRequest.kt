package br.com.football.player.skill.rest.request

import br.com.football.player.skill.exception.ErrorCode
import br.com.football.player.skill.validator.AtLeastOne
import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AttributeRequest(
        @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val category: String,
        @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
        @AtLeastOne(message = ErrorCode.VALUE_GREATER_EQUAL_THAN_ONE)
        val values: List<AttributeValueRequest>
)