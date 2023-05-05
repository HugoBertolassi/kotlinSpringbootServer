package br.com.alura.forum.dto

import br.com.alura.forum.model.Curso

data class TopicoDTO (
    val titulo:String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor:Long
)