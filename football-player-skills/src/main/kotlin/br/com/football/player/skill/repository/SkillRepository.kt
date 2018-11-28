package br.com.football.player.skill.repository

import br.com.football.player.skill.domain.Skill
import java.util.*


interface SkillRepository {

    fun save(entity: Skill)

    fun findByPlayerIDAndSeason(id: UUID, category: String): Skill

}