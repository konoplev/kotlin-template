package me.konoplev.template.db.repositories

import me.konoplev.template.db.tables.EntityTable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EntityRepository : CrudRepository<EntityTable, UUID>
