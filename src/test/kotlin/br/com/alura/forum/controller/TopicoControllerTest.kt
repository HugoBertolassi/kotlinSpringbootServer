package br.com.alura.forum.controller



import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext:WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object{
        private const val RECURSO = "/topicos/"
    }
    @BeforeEach //config de sempre fazer essa função antes de inicailizar os testes
    fun setup(){

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply<DefaultMockMvcBuilder?>(
                        //configurar a autenticação
                        SecurityMockMvcConfigurers.springSecurity()
                ).build()
    }

    //teste de autenticação

    @Test
    fun `Deve retornar codigo 400 quando chamar topicos sem token`(){
        //vamos testar com o proprio banco de dados pq nao usamos o TestContainer
        mockMvc.get(RECURSO).andExpect { status{ is4xxClientError()} }
    }

}