package br.com.alura.forum.model

object TopicoTest {
    fun build()=Topico(
            id=1,
            titulo="Kotlin Basico",
            mensagem="Aprendend kotlin basico",
            curso = CursoTest.build(),
            autor = UsuarioTest.build()
    )
}