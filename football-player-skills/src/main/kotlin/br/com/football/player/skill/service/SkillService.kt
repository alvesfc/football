package br.com.football.player.skill.service

import br.com.football.player.skill.domain.Skill
import br.com.football.player.skill.domain.SkillPK
import br.com.football.player.skill.repository.SkillRepository
import br.com.football.player.skill.rest.request.AdditionalInfoRequest
import br.com.football.player.skill.rest.request.AttributeRequest
import br.com.football.player.skill.rest.request.CreateSkillRequest
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SkillService(private val skillRepository: SkillRepository, private val objectMapper: ObjectMapper) {

    fun create(request: CreateSkillRequest) {

        skillRepository.save(Skill(SkillPK(id = request.playerID,
                season = request.season,
                createDate = LocalDateTime.now()),
                attributesJson = objectMapper.writeValueAsString(request.attributes),
                traits = objectMapper.writeValueAsString(request.traits),
                specialities = objectMapper.writeValueAsString(request.specialities),
                additionalInfo = objectMapper.writeValueAsString(request.additionalInfo)))

    }

    fun find(playerID: UUID, season: String): CreateSkillRequest {
        val entity = skillRepository.findByPlayerIDAndSeason(playerID, season)

        return CreateSkillRequest(playerID = entity.pk.id,
                season = entity.pk.season,
                traits = objectMapper.readValue(entity.traits, object : TypeReference<List<String>>() {}),
                specialities = objectMapper.readValue(entity.specialities, object : TypeReference<List<String>>() {}),
                attributes = objectMapper.readValue(entity.attributesJson, object : TypeReference<List<AttributeRequest>>() {}),
                additionalInfo = objectMapper.readValue(entity.additionalInfo, object : TypeReference<AdditionalInfoRequest>() {}))
    }
}