package br.com.alura.forum.service


import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
 public class TopicoService(
        private var topicos:List<Topico> = ArrayList(),
        private val cursoService:CursoService,
        private val autorService:AutorService
 ) {
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


        var dto=TopicoDTO(
                titulo = "alo",
                mensagem = "oi",
                idCurso = 1,
                idAutor = 1
        )


        topicos = Arrays.asList(topico,topico2)
        topicos = topicos.plus(topico3)
        cadastrar(dto)
    }
    fun listar(): List<TopicoView>{
        return topicos.stream().map{
            t->TopicoView(
                    id=t.id,
                    mensagem = t.mensagem,
                    titulo =  t.titulo,
                    status = t.status,
                    DataCriacao = t.dataCriacao
            )}.collect(Collectors.toList())//converte stream em uma lista
    }

    fun buscarPorId(id:Long): TopicoView {
        val t=topicos.stream().filter({
            it->it.id == id
        }).findFirst().get()
        return TopicoView(
                id=t.id,
                mensagem = t.mensagem,
                titulo =  t.titulo,
                status = t.status,
                DataCriacao = t.dataCriacao
        )
    }

    fun buscarPorIdTopico(id:Long): Topico {
        return topicos.stream().filter({
            it->it.id == id
        }).findFirst().get()
    }
    fun cadastrar(dto: TopicoDTO){
        println(dto)
        topicos=topicos.plus(Topico(
                id = topicos.size.toLong() + 1,
                titulo=dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.idCurso),
                autor = autorService.buscarPorId(dto.idAutor)
        ))
    }

}