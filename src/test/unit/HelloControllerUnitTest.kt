import com.ktprj.controller.HelloController
import com.ktprj.service.HelloService
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
@ContextConfiguration(classes = [HelloController::class])
@AutoConfigureWebTestClient
class HelloControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var helloService: HelloService

    @Test
    fun shouldGetGreetingFromHelloController() {
        val name: String = "doe"
        val responseValue = "hello from kotling universe mr. ${name}"

        every { helloService.sayHello(any()) } returns responseValue

        val result = webTestClient.get().uri("/hello/{name}", name).exchange()
            .expectStatus().is2xxSuccessful.expectBody(String::class.java).returnResult()

        Assertions.assertEquals(responseValue, result.responseBody)
    }
}