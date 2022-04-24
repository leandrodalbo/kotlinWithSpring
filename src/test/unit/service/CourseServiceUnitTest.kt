package service

import com.ktprj.KtApiApplication
import com.ktprj.dto.CourseDto
import com.ktprj.service.CourseService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.ConstraintViolationException

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [KtApiApplication::class])
class CourseServiceUnitTest {

    @Autowired
    lateinit var courseService: CourseService

    @Test
    fun shouldValidateACourseBeforeSavingIt() {

        assertThrows<ConstraintViolationException> {
            courseService.newCourse(CourseDto(null, "", "", 1))
        }

    }

    @Test
    fun shouldValidateTheIdIsNotRequiredToCreate() {

        assertThrows<Exception> {
            courseService.newCourse(CourseDto(234234, "", "", 1))
        }

    }


}