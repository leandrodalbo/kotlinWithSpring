package service

import com.ktprj.KtApiApplication
import com.ktprj.dto.CourseDto
import com.ktprj.service.CourseService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.validation.ConstraintViolationException

@SpringBootTest(classes = [KtApiApplication::class])
class CourseServiceUnitTest {

    @Autowired
    lateinit var courseService: CourseService

    @Test
    fun shouldValidateACourseBeforeSavingIt() {

        assertThrows<ConstraintViolationException> {
            courseService.newCourse(CourseDto(null, "", ""))
        }

    }

    @Test
    fun shouldValidateTheIdIsNotRequiredToCreate() {

        assertThrows<Exception> {
            courseService.newCourse(CourseDto(234234, "", ""))
        }

    }


}