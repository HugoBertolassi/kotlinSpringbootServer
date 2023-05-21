package br.com.alura.forum.config

import br.com.alura.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
        private val usuarioService: UsuarioService
) {
    private val expiration:Long=60000 //60000=1 minuto

    @Value("\${jwt.secret}")//pega o valor do yml  que define o valor do segredo da chave do yml
    private lateinit var  secret:String//lateInitapenas carrega a variavel quando gerar

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>):String?{
        return Jwts.builder()
                .setSubject(username)
                .claim("role", authorities)//pega as roles do usuario
                .setExpiration(Date(System.currentTimeMillis()+ expiration))
                .signWith(SignatureAlgorithm.HS512,secret.toByteArray())
                .compact()//junta as infs do token
    }

    fun isValid(jwt:String?):Boolean{
        return try{
            //valida o token
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        }catch (e:IllegalArgumentException){
            false
        }

    }

    fun getAuthentication(jwt:String?) : Authentication{
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user =usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null,  user.authorities)
    }
}