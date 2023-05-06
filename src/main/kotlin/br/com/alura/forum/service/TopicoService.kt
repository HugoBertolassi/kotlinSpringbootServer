package br.com.alura.forum.service


import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
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
        private val autorService:AutorService,
         private val topicoViewMapper:TopicoViewMapper,
         private val topicoFormMapper: TopicoFormMapper,
         private val notFoundMessage:String="Topico não encontrado"
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


        var dto=TopicoForm(
                titulo = "alo",
                mensagem = "oi",
                idCurso = 1,
                idAutor = 1
        )


        topicos = Arrays.asList(topico,topico2)
        topicos = topicos.plus(topico3)
        cadastrar(dto)
    }
   /*Funcao listar sem adocao do mapper, por nao ter o mapper fica tendo que repetir a construcao do objeto
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
    */

    fun listar(): List<TopicoView>{
        return topicos.stream().map{//coverte topico em topico view
            t->topicoViewMapper.map(t)
        }.collect(Collectors.toList())//converte stream em uma lista
    }
    fun buscarPorId(id:Long): TopicoView {
        val t=topicos.stream().filter({
            it->it.id == id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)} //o or else subbstituiu o get
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
    fun cadastrar(dto: TopicoForm):TopicoView{
        val topico =topicoFormMapper.map(dto)
        topico.id =topicos.size.toLong() + 1
        topicos=topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm) :TopicoView{
        val topico=topicos.stream().filter({
            it->it.id == form.id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)} //o or else subbstituiu o get
        val topicoAtualizado=Topico(
                id= form.id,
                titulo = form.titulo,
                mensagem=form.mensagem,
                autor = topico.autor,
                curso=topico.curso,
                respostas = topico.respostas,
                status=topico.status,
                dataCriacao = topico.dataCriacao
        )
        topicos=topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun excluirPorId(id: Long) {
        val topico=topicos.stream().filter({
            it->it.id == id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        topicos=topicos.minus(topico)
    }

    /*ex1 da função cadastrar
    fun cadastrar(dto: TopicoForm){
        //println(dto)
        topicos=topicos.plus(Topico(
                id = topicos.size.toLong() + 1,
                titulo=dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.idCurso),
                autor = autorService.buscarPorId(dto.idAutor)
        ))
    }*/
}