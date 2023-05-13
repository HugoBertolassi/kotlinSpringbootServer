package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper :MapperInterface<Topico, TopicoView>{
    override fun map(t: Topico): TopicoView {
       return TopicoView(
        id=t.id,
        mensagem = t.mensagem,
        titulo =  t.titulo,
        status = t.status,
        dataCriacao = t.dataCriacao
       )
    }
}