package controller

import com.ktprj.controller.InstructorController
import com.ktprj.dto.InstructorDto
import com.ktprj.service.InstructorService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest
@ContextConfiguration(classes = [InstructorController::class])
@AutoConfigureWebTestClient
class InstructorControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var instructorService: InstructorService

    @Test
    fun shouldPostAInstructor() {

        every { instructorService.newCourse(any()) } returns InstructorDto(
            1001, "Joe",
        )

        val result = webTestClient.post().uri("/instructors/create")
            .bodyValue(InstructorDto(null, "Joe"))
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(InstructorDto::class.java).returnResult().responseBody

        Assertions.assertEquals(result!!.name, "Joe")
        Assertions.assertNotNull(result.id)
    }
}