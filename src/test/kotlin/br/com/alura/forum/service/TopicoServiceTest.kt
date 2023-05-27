package br.com.alura.forum.service

import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicoServiceTest {
    //variaveis de teste
    val topico = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()


    //ambiene de teste
    val topicos:List<Topico> = ArrayList()
    val topicoRepository: TopicoRepository = mockk{
        every { findByCursoNome(any(),any())} returns topico
    }
    val topicoViewMapper:TopicoViewMapper = mockk()
    val topicoFormMapper:TopicoFormMapper = mockk()

    //oque realmente quero testar
    val topicoService = TopicoService(
       topicos, topicoRepository,topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`(){
        every{topicoViewMapper.map(any())} returns TopicoViewTest.build()
        topicoService.listar("Kotlin", paginacao)

        verify(exactly = 1){topicoRepository.findByCursoNome(any(),any())}
        verify(exactly = 1){topicoViewMapper.map(any())}
        verify(exactly = 0){topicoRepository.findAll(paginacao)}

    }
}