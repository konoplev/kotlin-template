package me.konoplev.template.api

import me.konoplev.template.BaseIntegrationTest
import me.konoplev.template.db.EntityDbRepository
import me.konoplev.template.db.EntityTable
import me.konoplev.template.domain.Entity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import java.util.*

class EntityResourceIntegrationTest(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val entityRepository: EntityDbRepository
): BaseIntegrationTest() {

    @Test
    fun `getEntityById returns the correct entity`() {
        // given
        val id = UUID.randomUUID()
        val entity = EntityTable(id, "Test")
        entityRepository.save(entity)

        // when
        val response = restTemplate.getForEntity("/api/v1/entities/$id", EntityDto::class.java)

        // then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(EntityDto(entity.id, "processed: Test"), response.body)
    }

    @Test
    fun `getEntityById returns 404 if entity is not found`() {
        // given
        val id = UUID.randomUUID()

        // when
        val response = restTemplate.getForEntity("/api/v1/entities/$id", String::class.java)

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}

