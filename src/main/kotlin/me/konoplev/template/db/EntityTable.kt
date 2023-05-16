package me.konoplev.template.db

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*


@Entity
@Table(name = "entity_table")
data class EntityTable(
    @Id
    val id: UUID,

    @Column(nullable = false)
    val name: String
) {
    constructor(name: String) : this(UUID.randomUUID(), name)
}

