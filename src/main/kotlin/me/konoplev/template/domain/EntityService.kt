package me.konoplev.template.domain

import me.konoplev.template.api.EntityDto
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class EntityService(private val entityRepository: EntityRepository<Entity, UUID>) {
    fun getEntity(id: UUID): Entity? = entityRepository.findById(id)?.let { Entity(it.id, processName(it.name)) }
    private fun processName(name: String): String = "processed: $name"
}
