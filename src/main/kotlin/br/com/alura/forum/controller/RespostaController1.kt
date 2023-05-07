package br.com.alura.forum.controller

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/*
@RestController
@RequestMapping("/respostas")
class RespostaController(
        private val serviceTopico:TopicoService,
        private val topicoViewMapper:TopicoViewMapper) {

    //criacao Hugo
   @GetMapping("/topico/{topicoId}")
    fun respostas(@PathVariable topicoId:Long):List<Resposta>{

        val usuario =Usuario(
            id=1,
            nome = "Joao",
            email="joao@email.com"
        )
        val topico1: Topico  =topicoViewMapper.map(serviceTopico.buscarPorId(1))
        val resposta1=Resposta(
            id = 1,
            mensagem = "Reposta do topico 1",
            autor= usuario,
            topico = topico1,
            solucao = true
        )
        val resposta2=Resposta(
            id = 2,
            mensagem = "Reposta 2 do topico 1",
            autor= usuario,
            topico = serviceTopico.buscarPorId(1),
            solucao = true
        )
        val resposta3=Resposta(
            id = 3,
            mensagem = "Reposta 1 do topico 2",
            autor= usuario,
            topico = serviceTopico.buscarPorId(2),
            solucao = true
        )

        val respostas=Arrays.asList(resposta1,resposta2,resposta3)
        return respostas.filter({ it ->
            it.topico.id == topicoId
        })
    }

    //Criacao aula
} */

@RestController
@RequestMapping("/topicos")
class RespostaController1(
        private val service: RespostaService
    ) {

    @GetMapping("/{id}/respostas")
    fun listar(@PathVariable id: Long): List<Resposta> {
        return service.listar(id)
    }

    @PostMapping("/respostas")
    @Transactional
    fun cadastrar(@RequestBody @Valid form: RespostaForm,
                  @PathVariable idTopico: Long){

        service.cadastrar(form)
    }

}