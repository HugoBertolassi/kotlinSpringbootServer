package br.com.alura.forum.dto

import br.com.alura.forum.mapper.MapperInterface
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RespostaForm(
        @field: NotEmpty @field:Size(min = 3, max = 100) val mensagem:String,
        @field:NotNull val idAutor:Long,
        @field:NotNull val idTopico:Long
)
