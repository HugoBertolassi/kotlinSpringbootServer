package br.com.alura.forum.dto

import br.com.alura.forum.mapper.MapperInterface
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import java.time.LocalDateTime

data class RespostaView(
        val id:Long?,
        val mensagem:String,
        val idAutor:Long,
        val idTopico:Long,
        val dataCriacao:LocalDateTime,
        val solucao:Boolean
)