package repository

import com.ktprj.KtApiApplication
import com.ktprj.entities.Course
import com.ktprj.entities.Instructor
import com.ktprj.repository.CourseRepository
import com.ktprj.repository.InstructorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ContextConfiguration(classes = [KtApiApplication::class])
class CourseRepositoryUnitTest {

    @Autowired
    lateinit var courseRepository: CourseRepository


    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setCourses() {
        val instructor = instructorRepository.save(Instructor(null, "Joe"))
        courseRepository.saveAll(
            listOf(
                Course(id = null, name = "abcx", category = "afdgxafdg", instructor),
                Course(id = null, name = "abcy", category = "afdgyag", instructor),
                Course(id = null, name = "yabc", category = "afgagyaga", instructor),
                Course(id = null, name = "abcz", category = "agzag", instructor),
                Course(id = null, name = "xabc", category = "agxagag", instructor)
            )
        )
    }

    @Test
    fun shouldFindCoursesContainingTheValueInTheName() {

        val value: String = "x"

        val result: List<Course> = courseRepository.findByNameContaining(value)

        Assertions.assertTrue(result.size == 2)

    }

    @Test
    fun shouldFindCoursesContainingTheValueInTheCategory() {

        val value: String = "y"

        val result: List<Course> = courseRepository.findByCategoryContaining(value)

        Assertions.assertTrue(result.size == 2)

    }
}