package me.konoplev.template.api

import io.mockk.every
import io.mockk.mockk
import me.konoplev.template.domain.Entity
import me.konoplev.template.domain.EntityService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.*

class EntityMapperTest {

    private val entityService: EntityService = mockk()
    private val entityMapper = EntityMapper(entityService)

    @Test
    fun `get returns entity if found`() {
        // given
        val id = UUID.randomUUID()
        val entityTable = Entity(id, "Test")
        every { entityService.getEntity(id) } returns entityTable

        // when
        val result = entityMapper.get(id)

        // then
        assertEquals(EntityDto(id, "Test"), result)
    }

    @Test
    fun `get returns null if entity not found`() {
        // given
        val id = UUID.randomUUID()
        every { entityService.getEntity(id) } returns null

        // when
        val result = entityMapper.get(id)

        // then
        assertNull(result)
    }
}

