package me.konoplev.template.api

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.*

class EntityResourceTest {
    @MockK
    lateinit var entityMapper: EntityMapper

    lateinit var entityResource: EntityResource

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        entityResource = EntityResource(entityMapper)
    }

    @Test
    fun `getEntityById returns entity when found`() {
        // given
        val id = UUID.randomUUID()
        val entity = EntityDto(id, "Test Entity")
        every { entityMapper.get(id) } returns entity

        // when
        val response = entityResource.getEntityById(id)

        // then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(entity, response.body)
    }

    @Test
    fun `getEntityById returns 404 when entity not found`() {
        // given
        val id = UUID.randomUUID()
        every { entityMapper.get(id) } returns null

        // when
        val response = entityResource.getEntityById(id)

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
    }
}

