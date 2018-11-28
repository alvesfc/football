package br.com.football.player.skill.domain

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table


@Table(value = "player_skills")
class Skill(
        @PrimaryKey
        val pk: SkillPK,
        @Column(value = "attributes_json")
        val attributesJson: String,
        @Column(value = "traits")
        val traits: String,
        @Column(value = "specialities")
        val specialities: String,
        @Column(value = "additionalInfo")
        val additionalInfo: String
)