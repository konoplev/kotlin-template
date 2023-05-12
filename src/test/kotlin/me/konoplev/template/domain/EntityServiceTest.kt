package me.konoplev.template.domain

import me.konoplev.template.db.repositories.EntityRepository
import me.konoplev.template.db.tables.EntityTable
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.*

class EntityServiceTest {

    private val entityRepository: EntityRepository = mockk()
    private val entityService = EntityService(entityRepository)

    @Test
    fun `get returns entity if found`() {
        // given
        val id = UUID.randomUUID()
        val entityTable = EntityTable(id, "Test")
        every { entityRepository.findById(id) } returns Optional.of(entityTable)

        // when
        val result = entityService.get(id)

        // then
        assertEquals(Entity(id, "Test"), result)
    }

    @Test
    fun `get returns null if entity not found`() {
        // given
        val id = UUID.randomUUID()
        every { entityRepository.findById(id) } returns Optional.empty()

        // when
        val result = entityService.get(id)

        // then
        assertNull(result)
    }
}

