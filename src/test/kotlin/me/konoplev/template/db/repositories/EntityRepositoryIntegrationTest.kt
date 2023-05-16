package me.konoplev.template.db.repositories

import me.konoplev.template.BaseIntegrationTest
import me.konoplev.template.db.EntityDbRepository
import me.konoplev.template.db.EntityTable
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*


class EntityRepositoryIntegrationTest(@Autowired val entityRepository: EntityDbRepository): BaseIntegrationTest() {

    @Test
    fun `getById returns the correct entity`() {
        // given
        var id = UUID.randomUUID()
        val entity = EntityTable(id, "Test")
        entityRepository.save(entity)

        // when
        val retrievedEntity: Optional<EntityTable> = entityRepository.findById(id)

        // then
        assertThat("Unexpected entity found", retrievedEntity, `is`(Optional.of(entity)))
    }

    @Test
    fun `getById returns null when there is no entity with the specified ID`() {
        // given
        val nonExistentId = UUID.randomUUID()

        // when
        val retrievedEntity = entityRepository.findById(nonExistentId)

        // then
        assertThat("Unexpected entity found", retrievedEntity.isEmpty)
    }
}
