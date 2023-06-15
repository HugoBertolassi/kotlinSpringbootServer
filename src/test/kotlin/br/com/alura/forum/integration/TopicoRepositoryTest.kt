package br.com.alura.forum.integration

import br.com.alura.forum.configuration.DatabaseContainerConfiguration
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers


@DataJpaTest //habilita trazer infos de query eoutras coisas
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//isso informa para o spring que não é para udar o db pelo db de teste

class TopicoRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var repository: TopicoRepository

    private val paginacao = PageRequest.of(0,5)
    private val topico = TopicoTest.build()

//    init{
//        @Autowired
//
//    }
//    @BeforeEach
//    fun setUp(){
//        topicoRepository
//    }

//    companion object {
//        @Container //criando o container com o db de teste
//        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
//            withDatabaseName("testdb")
//            withUsername("joao")
//            withPassword("12345")
//            //withReuse(true)
//        }
//        @Container
//        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply{
//            withExposedPorts(6379)
//        }
//
//        @JvmStatic
//        @DynamicPropertySource
//        fun properties(registry: DynamicPropertyRegistry) {
//            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
//            registry.add("spring.datasource.password", mysqlContainer::getPassword);
//            registry.add("spring.datasource.username", mysqlContainer::getUsername);
//
//            registry.add("spring.redis.host", redisContainer::  getContainerIpAddress)
//            registry.add("spring.redis.port", redisContainer::  getFirstMappedPort)
//        }
//
//
//    }

//    fun saveTopicoRepository(){
//        if(this::topicoRepository.isInitialized){
//            topicoRepository.save(topico)
//        }
//        else{
//            delay(Duration.ofSeconds(5))
//            topicoRepository.save(topico)
//        }
//    }

    @Test
    fun `deve gerar um relatorio`() {
        if(this::repository.isInitialized){
            println("inicializou sim")
        }
        else{
            println("não incou pq")
        }
        //saveTopicoRepository()
        repository.save(topico)
        val relatorio = repository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
    }

    @Test
    fun `deve buscar um topico por nome`() {
        repository.save(topico)
        val resultado = repository.findByCursoNome(nomeCurso = "Kotlin", paginacao = paginacao)

        assertThat(resultado.totalElements).isEqualTo(1)
    }
}