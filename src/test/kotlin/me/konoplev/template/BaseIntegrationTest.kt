package me.konoplev.template

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = ["spring.jpa.hibernate.ddl-auto=validate"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class BaseIntegrationTest {
    companion object {
        private val container: PostgreSQLContainer<Nothing> = PostgreSQLContainer<Nothing>("postgres:14.1").apply {
            withDatabaseName("test")
            withUsername("test")
            withPassword("test")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            initialize(registry)
        }

        private fun initialize(registry: DynamicPropertyRegistry) {
            container.start()
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.username", container::getUsername)
            registry.add("spring.datasource.password", container::getPassword)
        }

        @AfterAll
        fun teardown() {
            container.stop()
        }

        @TestExecutionListeners(InitializableTestExecutionListener::class)
        abstract class InitializableTestExecutionListener : TestExecutionListener {
            override fun beforeTestMethod(testContext: TestContext) {
                if (!container.isRunning) {
                    initialize(testContext.applicationContext.getBean(DynamicPropertyRegistry::class.java))
                }
            }
        }
    }

    protected fun stopDb() {
        container.stop()
    }
}
