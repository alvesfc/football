package br.com.football.player.skill.rest.request

import br.com.football.player.skill.exception.ErrorCode
import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.Pattern

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AdditionalInfoRequest(
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val preferredFoot: String,
        val internationalReputation: Int,
        val weakFoot: Int,
        val skillMoves: Int,
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val workRate: String,
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val bodyType: String,
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val realFace: Boolean,
        @Pattern(message = ErrorCode.FIELD_IS_INVALID, regexp = "^.{3,30}$")
        val releaseClause: String
)
