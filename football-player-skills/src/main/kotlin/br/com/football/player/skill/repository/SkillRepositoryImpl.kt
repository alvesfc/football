package br.com.football.player.skill.repository

import br.com.football.player.skill.domain.Skill
import com.datastax.driver.core.querybuilder.QueryBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SkillRepositoryImpl : SkillRepository {

    val cassandraTemplate: CassandraTemplate

    @Autowired
    constructor(cassandraTemplate: CassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate
    }

    override fun save(entity: Skill) {
        cassandraTemplate.insert(entity)
    }

    override fun findByPlayerIDAndSeason(id: UUID, season: String): Skill {
        val tableName = Skill::class.java.getAnnotation(Table::class.java).value

        val select = QueryBuilder.select().from(tableName)

        select.where(QueryBuilder.eq("player_id", id))
                .and(QueryBuilder.eq("season", season))

        return cassandraTemplate.selectOne(select, Skill::class.java)

    }
}