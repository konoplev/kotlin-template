package me.konoplev.template.api

import me.konoplev.template.domain.Entity
import me.konoplev.template.domain.EntityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/v1/entities")
class EntityResource(private val entityService: EntityService) {
    @GetMapping("/{id}")
    @Operation(
        summary = "Get Entity by ID",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Entity found", content = [Content(
                    schema = Schema(
                        implementation = Entity::class
                    )
                )]
            ),
            ApiResponse(responseCode = "404", description = "Entity not found")
        ]
    )
    fun getEntityById(
        @PathVariable @Parameter(description = "Entity ID") id: UUID
    ): ResponseEntity<Entity> =
        entityService.get(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}
