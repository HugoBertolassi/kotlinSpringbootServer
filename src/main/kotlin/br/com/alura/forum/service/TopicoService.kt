package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
 public class TopicoService(private var topicos:List<Topico>) {
    init{
        val topico = Topico(
            id = 1,
            titulo = "duvida de Kotlin",
            mensagem = "Variaveisde kotlim",
            curso = Curso(
                id = 1,
                nome="Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "joao",
                email = "joao@email.com"
            )
        )
        val topico2 = Topico(
            id = 2,
            titulo = "duvida de Kotlin2",
            mensagem = "Variaveisde kotlim2",
            curso = Curso(
                id = 1,
                nome="Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "joao",
                email = "joao@email.com"
            )
        )
        val topico3 = Topico(
            id = 3,
            titulo = "duvida de Kotlin3",
            mensagem = "Variaveisde kotlim",
            curso = Curso(
                id = 1,
                nome="Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "joao",
                email = "joao@email.com"
            )
        )
        topicos = Arrays.asList(topico,topico2,topico3)
    }
    fun listar(): List<Topico>{
        return topicos
    }

    fun buscarPorId(id:Long): Topico {
        return topicos.stream().filter({
                it->it.id == id
        }).findFirst().get()
    }

}