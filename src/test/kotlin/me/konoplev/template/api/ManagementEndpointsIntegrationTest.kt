package me.konoplev.template.api

import me.konoplev.template.BaseIntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class ManagementEndpointsIntegrationTest(
    @Autowired
    private val restTemplate: TestRestTemplate,
    @Autowired
    private val healthEndpoint: HealthEndpoint
) : BaseIntegrationTest() {

    @Test
    fun testHealthEndpoint() {
        // Check that the health endpoint is available
        var response = restTemplate.getForEntity("/actuator/health", String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("UP", healthEndpoint.health().status.toString())

        // Stop the database container to make the database unavailable
        stopDb()

        // Check that the health endpoint returns an unhealthy status when the database is unavailable
        response = restTemplate.getForEntity("/actuator/health", String::class.java)
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.statusCode)
        assertEquals("DOWN", healthEndpoint.health().status.toString())

    }

}
