package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService (
        var cursos:List<Curso>,
        private val repository:CursoRepository
){
    /*init{
        val curso = Curso(
                id = 1,
                nome = "Kotlin"
        )
        cursos = listOf(curso)
    }*/

    fun buscarPorId(id:Long):Curso{
        return repository.getOne(id)
    }
    fun buscarPorIdMemoria(id:Long):Curso{
        return cursos.stream().filter({
            c->c.id==id
        }).findFirst().get()
    }

}
