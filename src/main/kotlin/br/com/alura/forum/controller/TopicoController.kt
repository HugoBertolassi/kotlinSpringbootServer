package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
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
    @Transactional //é necessa´rio colocar nos metodos de escrita
    fun cadastrar(@RequestBody @Valid dto:TopicoForm,
                  uriBuilder:UriComponentsBuilder
                  ):ResponseEntity<TopicoView> {
        val topicoview=service.cadastrar(dto)
        val uri=uriBuilder.path("/topicos/${topicoview.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoview)
    }

    @PutMapping
    @Transactional
    fun atualizar(
            @RequestBody @Valid form: AtualizacaoTopicoForm,
                  ) :ResponseEntity<TopicoView>{
        val topicoview=service.atualizar(form)
        return ResponseEntity.ok(topicoview)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun excluir(@PathVariable id:Long){
        service.excluirPorId(id)
    }
}