package me.konoplev.template.domain

import me.konoplev.template.db.repositories.EntityRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EntityService(private val entityRepository: EntityRepository) {
    fun get(id: UUID): Entity? =
        entityRepository.findById(id).map { Entity(it.id, it.name) }.orElse(null)
}
