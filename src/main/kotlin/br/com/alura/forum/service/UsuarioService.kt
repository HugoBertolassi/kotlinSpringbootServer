package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
        var autores:List<Usuario>,
        private val repository:UsuarioRepository
) {

    /*init {
        val autor=Usuario(
                id = 1,
                nome="joao",
                email="jaoa@email.com"
        )
        autores= listOf(autor)
    }*/

    fun buscarPorId(id:Long): Usuario {
        return repository.getOne(id)
    }

    //pega o valor local de usuarios
/*    fun buscarPorIdMemoria(id:Long): Usuario {
        return autores.stream().filter({
            c->c.id==id
        }).findFirst().get()
    }
*/
}
