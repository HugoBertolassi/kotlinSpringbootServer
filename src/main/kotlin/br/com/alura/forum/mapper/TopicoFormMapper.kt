package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.AutorService
import br.com.alura.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService:CursoService,
        private val autorService: AutorService
):MapperInterface<TopicoForm,Topico> {
    override fun map(t: TopicoForm): Topico {
        return Topico(
                titulo=t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.buscarPorId(t.idCurso),
                autor = autorService.buscarPorId(t.idAutor)
        )
    }

}