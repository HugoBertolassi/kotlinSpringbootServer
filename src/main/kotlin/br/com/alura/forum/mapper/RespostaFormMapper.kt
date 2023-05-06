package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.AutorService
import br.com.alura.forum.service.TopicoService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
        val autorService:AutorService,
        val topicoService: TopicoService
):MapperInterface<RespostaForm,Resposta> {
    override fun map(t: RespostaForm): Resposta {
        return Resposta(
                mensagem = t.mensagem,
                autor= autorService.buscarPorId(t.idAutor),
                topico = topicoService.buscarPorId(t.idTopico)
        )
    }
}