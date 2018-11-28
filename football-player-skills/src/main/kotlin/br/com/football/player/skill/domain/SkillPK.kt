package br.com.football.player.skill.domain

import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.time.LocalDateTime
import java.util.*

@PrimaryKeyClass
data class SkillPK(@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "player_id")
                       val id: UUID,
                   @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "season")
                       val season: String,
                   @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "create_date", ordering = Ordering.DESCENDING)
                       val createDate: LocalDateTime = LocalDateTime.now())