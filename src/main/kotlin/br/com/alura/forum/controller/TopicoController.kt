package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.RespostaService
import br.com.alura.forum.service.TopicoService
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service:TopicoService) {

    @GetMapping
    fun listar():List<TopicoView>{
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id:Long):TopicoView{
        return service.buscarPorId(id)
    }
    @PostMapping
    fun cadastrar(@RequestBody dto:TopicoDTO) {
        service.cadastrar(dto)
    }
}