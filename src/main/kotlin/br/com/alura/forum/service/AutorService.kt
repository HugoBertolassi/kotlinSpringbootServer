package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class AutorService(
        var autores:List<Usuario>
) {

    init {
        val autor=Usuario(
                id = 1,
                nome="joao",
                email="jaoa@email.com"
        )
        autores= listOf(autor)
    }

    fun buscarPorId(id:Long): Usuario {
        return autores.stream().filter({
            c->c.id==id
        }).findFirst().get()
    }

}
