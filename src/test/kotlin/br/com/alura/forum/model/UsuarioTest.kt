package br.com.alura.forum.model

object UsuarioTest {
    fun build()= Usuario(
            id = 1, nome = "Joao", email = "jvc.martins", password = "123"
    )
    fun buildToToken() = Usuario(
            nome = "joao",
            email = "joao@email.com",
            password = "123456")

}
