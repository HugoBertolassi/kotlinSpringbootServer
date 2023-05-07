package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoView
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Resposta(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//é colocado pq o código que vi criar
        var id:Long? =  null,
        val mensagem:String,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val autor:Usuario,
        @ManyToOne
        val topico: Topico,
        val solucao:Boolean=false
)
