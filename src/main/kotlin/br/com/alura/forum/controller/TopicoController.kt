package br.com.alura.forum.controller

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController {

    @GetMapping
    fun listar():List<Topico>{
        val topico =Topico(
            id =1,
            titulo = "Duvida de kotlin",
            mensagem = "Variaveis de Kotlin",
            curso= Curso(
                    id =1,
                    nome= "kotlin",
                    categoria ="Programacao"
                    ),
            autor = Usuario(
                id = 1,
                nome = "Joao",
                email = "joao@email.com"
                )
        )
        return Arrays.asList(topico,topico,topico)
    }
}