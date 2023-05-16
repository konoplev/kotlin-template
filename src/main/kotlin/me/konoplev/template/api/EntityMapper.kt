package me.konoplev.template.api

import me.konoplev.template.domain.EntityService
import org.springframework.stereotype.Service
import java.util.*

@Service
class EntityMapper(val entityService: EntityService) {
    fun get(id: UUID): EntityDto? = entityService.getEntity(id)?.let { EntityDto(it.id, it.name) }
}
