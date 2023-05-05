package br.com.alura.forum.controller

import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.RespostaService
import br.com.alura.forum.service.TopicoService
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Arrays
import java.util.stream.Collectors

@RestController
@RequestMapping("/respostas")
class RespostaController(private val serviceTopico:TopicoService) {

    //criacao Hugo
    @GetMapping("/topico/{topicoId}")
    fun respostas(@PathVariable topicoId:Long):List<Resposta>{

        val usuario =Usuario(
            id=1,
            nome = "Joao",
            email="joao@email.com"
        )
        val resposta1=Resposta(
            id = 1,
            mensagem = "Reposta do topico 1",
            autor= usuario,
            topico = serviceTopico.buscarPorIdTopico(1),
            solucao = true
        )
        val resposta2=Resposta(
            id = 2,
            mensagem = "Reposta 2 do topico 1",
            autor= usuario,
            topico = serviceTopico.buscarPorIdTopico(1),
            solucao = true
        )
        val resposta3=Resposta(
            id = 3,
            mensagem = "Reposta 1 do topico 2",
            autor= usuario,
            topico = serviceTopico.buscarPorIdTopico(2),
            solucao = true
        )

        val respostas=Arrays.asList(resposta1,resposta2,resposta3)
        return respostas.filter({ it ->
            it.topico.id == topicoId
        })
    }

    //Criacao aula
}

@RestController
@RequestMapping("/topicos/{id}/respostas")
class RespostasController(private val service: RespostaService) {

    @GetMapping
    fun listar(@PathVariable id: Long): List<Resposta> {
        return service.listar(id)
    }

}