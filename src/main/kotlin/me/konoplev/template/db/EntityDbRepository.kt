package me.konoplev.template.db

import me.konoplev.template.domain.Entity
import me.konoplev.template.domain.EntityRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Repository
interface EntityDbRepository : CrudRepository<EntityTable, UUID>


@Service
class EntityDbRepositoryAdapter(private val dbRepo: EntityDbRepository): EntityRepository<Entity, UUID> {
    override fun findById(id: UUID): Entity? = dbRepo.findById(id).map { Entity(it.id, it.name) }.orElse(null)
}
