package br.com.alura.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Usuario(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//é colocado pq o código que vi criar
        val id: Long?=null,
        val nome:String,
        val email:String,
        val password:String,

        @JsonIgnore//usado para não mostrar a lista, o que pode ocasionar um stack overflow
        @ManyToMany(fetch = FetchType.EAGER)//carrega a tabela de usuario
        @JoinColumn(name = "usuario_role")
        val role:List<Role> = mutableListOf()
)
