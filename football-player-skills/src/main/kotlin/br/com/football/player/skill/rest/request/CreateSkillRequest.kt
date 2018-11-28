package br.com.football.player.skill.rest.request

import br.com.football.player.skill.exception.ErrorCode
import br.com.football.player.skill.validator.AtLeastOne
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateSkillRequest(

        @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")
        val playerID: UUID,
        @NotBlank(message = ErrorCode.FIELD_IS_MANDATORY)
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val season: String,
        @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
        @AtLeastOne(message = ErrorCode.VALUE_GREATER_EQUAL_THAN_ONE)
        val traits: List<String>,
        @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
        @AtLeastOne(message = ErrorCode.VALUE_GREATER_EQUAL_THAN_ONE)
        val specialities: List<String>,
        @NotNull(message = ErrorCode.FIELD_IS_MANDATORY)
        @AtLeastOne(message = ErrorCode.VALUE_GREATER_EQUAL_THAN_ONE)
        val attributes: List<AttributeRequest>,
        val additionalInfo: AdditionalInfoRequest
)