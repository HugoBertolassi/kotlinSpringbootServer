package br.com.alura.forum.service

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.*
import br.com.alura.forum.repository.RespostaRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class RespostaService(
        private var respostas: List<Resposta> = ArrayList(),
        //private val usuarioService: UsuarioService,
        //private val topicoService: TopicoService,
        private val respostaFormMapper:RespostaFormMapper,
        private val respostaRepository: RespostaRepository,
        private val respostaViewMapper: RespostaViewMapper
        ) {
/*
    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programacao"
        )
        val autor = Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "ana@email.com"
        )
        val topico = TopicoView(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            status = StatusTopico.SOLUCIONADO,
                DataCriacao = LocalDateTime.now()

        )

        val resposta1 = Resposta(
            id = 1,
            mensagem = "Resposta 1",
            autor = autor,
            topico = topico,
            solucao = false
        )

        val resposta2 = Resposta(
            id = 2,
            mensagem = "Resposta 2",
            autor = autor,
            topico = topico,
            solucao = false
        )

        respostas = Arrays.asList(resposta1, resposta2)
    }*/

//    fun listar(idTopico: Long): List<Resposta> {
//        return respostas.stream().filter { r ->
//            r.topico.id == idTopico
//        }.collect(Collectors.toList())
//    }

    fun listar(idTopico: Long): List<RespostaView> {
        println("idTopico $idTopico")
       val respostas = respostaRepository.findByTopicoId(idTopico)
        return respostas.map{
            resposta-> respostaViewMapper.map(resposta)
        }
    }
    fun listarPorTopico(idTopico: Long): List<Resposta> {
        return respostas.stream().filter { r ->
            r.topico.id == idTopico
        }.collect(Collectors.toList())
    }

    fun cadastrar(respostaForm: RespostaForm){
        val resposta = respostaFormMapper.map(respostaForm)
        resposta.id = respostas.size.toLong() + 1
        respostas=respostas.plus(resposta)
    }

    fun salvar(respostaForm: RespostaForm) {

        val resposta = respostaFormMapper.map(respostaForm)
        respostaRepository.save(resposta)
    }

}