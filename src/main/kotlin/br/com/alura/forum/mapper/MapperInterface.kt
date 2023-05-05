package br.com.alura.forum.mapper

interface MapperInterface<T, U> {
    fun map(t:T):U
}
