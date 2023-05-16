package me.konoplev.template.domain

import io.mockk.every
import io.mockk.mockk
import me.konoplev.template.domain.Entity
import me.konoplev.template.domain.EntityRepository
import me.konoplev.template.domain.EntityService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.*

class EntityServiceTest {

    private val entityRepository: EntityRepository<Entity, UUID> = mockk()
    private val entityService = EntityService(entityRepository)

    @Test
    fun `getEntity returns entity if found`() {
        // given
        val id = UUID.randomUUID()
        val entity = Entity(id, "Test")
        every { entityRepository.findById(id) } returns entity

        // when
        val result = entityService.getEntity(id)

        // then
        assertEquals(Entity(id, "processed: Test"), result)
    }

    @Test
    fun `getEntity returns null if entity not found`() {
        // given
        val id = UUID.randomUUID()
        every { entityRepository.findById(id) } returns null

        // when
        val result = entityService.getEntity(id)

        // then
        assertNull(result)
    }
}
