package br.com.alura.forum.integration

import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest //habilita trazer infos de query eoutras coisas
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//isso informa para o spring que não é para udar o db pelo db de teste
class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    private val paginacao = PageRequest.of(0,5)
    private val topico = TopicoTest.build()

    companion object {
        @Container //criando o container com o db de teste
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("testdb")
            withUsername("joao")
            withPassword("12345")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
            registry.add("spring.datasource.password", mysqlContainer::getPassword);
            registry.add("spring.datasource.username", mysqlContainer::getUsername);
        }
    }

    @Test
    fun `deve gerar um relatorio`() {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
    }

    @Test
    fun `deve buscar um topico por nome`() {
        topicoRepository.save(topico)
        val resultado = topicoRepository.findByCursoNome(nomeCurso = "Kotlin", paginacao = paginacao)

        assertThat(resultado.totalElements).isEqualTo(1)
    }
}