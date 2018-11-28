package br.com.football.player.skill.rest

import br.com.football.player.skill.rest.request.CreateSkillRequest
import br.com.football.player.skill.service.SkillService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/skills")
class SkillsResource(private val skillService: SkillService) {

    @PostMapping(consumes = [(APPLICATION_JSON_VALUE)], produces = [(APPLICATION_JSON_VALUE)])
    @ResponseBody
    fun create(@Valid @RequestBody createRequest: CreateSkillRequest): ResponseEntity<UUID> {

        skillService.create(createRequest)

        return ResponseEntity.created(URI.create("/skills/${createRequest.playerID}")).build()
    }

    @GetMapping(value = [("/{id}")], consumes = [(APPLICATION_JSON_VALUE)], produces = [(APPLICATION_JSON_VALUE)])
    @ResponseBody
    fun read(@PathVariable(value = "id") id: UUID, @RequestParam(value = "season") season: String): ResponseEntity<CreateSkillRequest> {

        return ResponseEntity
                .ok()
                .body(skillService.find(id, season))
    }
}