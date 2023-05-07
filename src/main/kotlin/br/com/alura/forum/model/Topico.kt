package br.com.alura.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

import java.time.LocalDateTime

@Entity
data class Topico (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//é colocado pq o código que vi criar
        var id:Long?=null,
        var titulo:String,
        var mensagem:String,
        val dataCriacao:LocalDateTime = LocalDateTime.now(),
        var dataUltimaAlteracao:LocalDateTime = LocalDateTime.now(),
        @ManyToOne//adição da cardinalidade do banco
        val curso:Curso,
        @ManyToOne
        val autor:Usuario,
        @Enumerated(value= EnumType.STRING)
        val status:StatusTopico = StatusTopico.NAO_RESPONDIDO,
        @OneToMany(mappedBy = "topico")//di que é outro atributo que controla
        val respostas: List<Resposta> = ArrayList()

)

