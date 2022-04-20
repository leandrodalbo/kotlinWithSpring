import com.ktprj.KtApiApplication
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = arrayOf(KtApiApplication::class))
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class HelloControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun shouldGetGreetingFromHelloController() {
        val name: String = "doe"
        val result = webTestClient.get().uri("/hello/{name}", name).exchange()
            .expectStatus().is2xxSuccessful.expectBody(String::class.java).returnResult()

        Assertions.assertEquals("hello from kotling universe mr. ${name}", result.responseBody)
    }
}