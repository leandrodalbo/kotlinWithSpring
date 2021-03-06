package controller

import com.ktprj.controller.CourseController
import com.ktprj.dto.CourseDto
import com.ktprj.service.CourseService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder

@WebMvcTest
@ContextConfiguration(classes = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseService: CourseService

    @Test
    fun shouldPostAnewCourse() {

        every { courseService.newCourse(any()) } returns CourseDto(
            0, "X", "Y", 1
        )

        val result = webTestClient.post().uri("/courses/create")
            .bodyValue(CourseDto(null, "X", "Y", 1)).exchange()
            .expectStatus().is2xxSuccessful.expectBody(CourseDto::class.java).returnResult().responseBody

        Assertions.assertEquals(result!!.category, "Y")
        Assertions.assertEquals(result.name, "X")
        Assertions.assertNotNull(result.id)
    }


    @Test
    fun willGetAll() {

        every { courseService.getAll(any()) }.returnsMany(
            listOf(
                CourseDto(
                    0, "X", "Y", 1
                ), CourseDto(
                    1, "A", "B", 1
                )

            )
        )

        val result = webTestClient.get().uri("/courses/all").exchange()
            .expectStatus().is2xxSuccessful.expectBody(List::class.java).returnResult().responseBody

        Assertions.assertTrue(result!!.size == 2)

    }

    @Test
    fun willGetAllWithNameLike() {

        every { courseService.getAll(any()) }.returnsMany(
            listOf(
                CourseDto(
                    0, "axbd", "Y", 1
                )
            )
        )

        val uri = UriComponentsBuilder
            .fromUriString("/courses/all")
            .queryParam("x")
            .toUriString()

        val result = webTestClient.get().uri(uri).exchange()
            .expectStatus().is2xxSuccessful.expectBody(List::class.java).returnResult().responseBody

        Assertions.assertTrue(result!!.size == 1)

    }

    @Test
    fun willUpdateACourse() {

        every { courseService.updateCourse(any(), any()) } returns CourseDto(2235, "X", "Y", 1)

        val result = webTestClient.put().uri("/courses/update/{id}", 2235)
            .bodyValue(CourseDto(2235, "A", "B", 1))
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(CourseDto::class.java).returnResult().responseBody

        Assertions.assertTrue(result!!.name == "X")
        Assertions.assertTrue(result.category == "Y")
        Assertions.assertTrue(result.id == 2235)

    }


    @Test
    fun willDeleteACourse() {

        every { courseService.deleteCourse(any()) } returns "DELETED ${2235}"

        val result = webTestClient.delete().uri("/courses/delete/{id}", 2235)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java).returnResult().responseBody

        Assertions.assertTrue(result != null)


    }
}