package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RespostaViewMapper(
):MapperInterface<Resposta, RespostaView>{

    override fun map(r: Resposta): RespostaView {
        return RespostaView(
                id = r.id,
                mensagem = r.mensagem,
                idAutor = r.autor.id ?:0,
                idTopico = r.topico.id ?:0,
                dataCriacao = r.dataCriacao,
                solucao= r.solucao
        )
    }
}