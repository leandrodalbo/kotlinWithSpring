import com.ktprj.KtApiApplication
import com.ktprj.dto.CourseDto
import com.ktprj.entities.Course
import com.ktprj.repository.CourseRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.server.ResponseStatusException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = arrayOf(KtApiApplication::class))
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var repository: CourseRepository

    @BeforeEach
    fun cleanDataBase() {
        repository.deleteAll()
    }

    @Test
    fun willGetNotFoundIfDeletingANoneExistingCourse() {
        val errorResponse = webTestClient.delete().uri("/courses/delete/{id}", 22323)
            .exchange()
            .expectStatus().isBadRequest.expectBody(String::class.java).returnResult().responseBody

        Assertions.assertTrue(errorResponse!!.contains("400"))
    }

    @Test
    fun willDeleteAnExistingCourse() {
        val deletingCourse = repository.save(Course(null, "x", "y"))

        val deletedCourse = webTestClient.delete().uri("/courses/delete/{id}", deletingCourse.id)
            .exchange().expectStatus().isOk.expectBody(
                String::class.java
            ).returnResult().responseBody

        Assertions.assertEquals("DELETED ${deletingCourse.id}", deletedCourse)
    }

    @Test
    fun willUpdateExistingCourse() {
        val updatingCourse = repository.save(Course(null, "x", "y"))

        val updatedCourse = webTestClient.put().uri("/courses/update/{id}", updatingCourse.id)
            .bodyValue(CourseDto(id = null, name = "a", category = "b")).exchange().expectStatus().isOk.expectBody(
                CourseDto::class.java
            ).returnResult().responseBody

        Assertions.assertTrue(updatedCourse?.id == updatingCourse.id)
        Assertions.assertTrue(updatedCourse?.name != updatingCourse.name)
        Assertions.assertTrue(updatedCourse?.category != updatingCourse.category)
    }

    @Test
    fun willGetNotFoundIfUpdatingANoneExistingCourse() {
        val errorResponse = webTestClient.put().uri("/courses/update/{id}", 22323)
            .bodyValue(CourseDto(id = null, name = "x", category = "y")).exchange()
            .expectStatus().isBadRequest.expectBody(String::class.java).returnResult().responseBody

        Assertions.assertTrue(errorResponse!!.contains("400"))
    }

    @Test
    fun willFindAllCoursesButThereIsNotASingleOne() {
        val emptyListOfCourses = webTestClient.get().uri("/courses/all").exchange()
            .expectStatus().is2xxSuccessful.expectBody(List::class.java).returnResult().responseBody

        Assertions.assertTrue(emptyListOfCourses!!.isEmpty())
    }

    @Test
    fun canNotProvideAnIdForANewCourse() {
        val reqCourseBody: CourseDto = CourseDto(1, "x", "y")

        val exceptionResponse = webTestClient.post().uri("/courses/create").bodyValue(reqCourseBody).exchange()
            .expectStatus().isUnauthorized.expectBody(String::class.java).returnResult().responseBody

        Assertions.assertTrue(exceptionResponse!!.contains("401"))

    }

    @Test
    fun shouldCreateANewCourse() {
        val reqCourseBody: CourseDto = CourseDto(null, "x", "y")


        val createdCourse = webTestClient.post().uri("/courses/create").bodyValue(reqCourseBody).exchange()
            .expectStatus().is2xxSuccessful.expectBody(CourseDto::class.java).returnResult().responseBody

        Assertions.assertNotEquals(reqCourseBody, createdCourse)
        Assertions.assertTrue(createdCourse?.id != null)
    }
}