package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.service.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SecurityRequirement(name= "bearerAuth")
@RequestMapping("/relatorios")
class RelatorioController (
        private val topicoService: TopicoService
){
    @GetMapping
    fun relatorio():List<TopicoPorCategoriaDto>{
        return topicoService.relatorio()
    }
}