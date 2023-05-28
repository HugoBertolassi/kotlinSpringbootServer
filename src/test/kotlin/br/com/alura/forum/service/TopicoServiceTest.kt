package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*
import kotlin.collections.ArrayList

class TopicoServiceTest {
    //variaveis de teste
    val topico = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()


    //ambiene de teste
    val topicos:List<Topico> = ArrayList()
    val topicoRepository: TopicoRepository = mockk{
        every { findByCursoNome(any(),any())} returns topico
        every { findAll(paginacao)} returns topico
    }
    val slot = slot<Topico>()//config de captura antes da modificação
    val topicoViewMapper:TopicoViewMapper = mockk(){
        every{map(any())} returns TopicoViewTest.build()
        every { map(capture(slot)) } returns TopicoViewTest.build()

    }
    val topicoFormMapper:TopicoFormMapper = mockk()

    //oque realmente quero testar
    val topicoService = TopicoService(
       topicos, topicoRepository,topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`(){
        //val slot = slot<Topico>()//função slot permite capturar a chamada e verificar os valores recebidos
        //every { topicoViewMapper.map(capture(slot)) } returns TopicoViewTest.build()

        val topico = TopicoTest.build() //cria opico para verificar com o valor capturado durante a trasnformação para mapper
        topicoService.listar("Kotlin", paginacao)

        verify(exactly = 1){topicoRepository.findByCursoNome(any(),any())}
        verify(exactly = 1){topicoViewMapper.map(any())}
        verify(exactly = 0){topicoRepository.findAll(paginacao)}

        //assim é possivel verificar se o tipo Topico está correto
        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }

    @Test
    fun `deve listar topicos quando o nome do curso for nulo`(){

        val topico = TopicoTest.build()

        topicoService.listar(null, paginacao)

        verify(exactly = 0){topicoRepository.findByCursoNome(any(),any())}
        verify(exactly = 1){topicoViewMapper.map(any())}
        verify(exactly = 1){topicoRepository.findAll(paginacao)}

        //assim é possivel verificar se o tipo Topico está correto
        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }

    @Test
    fun `deve lancar excecao se nao achar topico por id`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(2)
        }

        assertThat(actual.message).isEqualTo("Topico não encontrado")
    }
}