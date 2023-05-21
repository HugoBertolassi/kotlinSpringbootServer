package br.com.alura.forum.security

import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.model.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
       private val authManager: AuthenticationManager,
       private val  jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter(){

    //aqui vamos interceptar a chamada http e verificar se ele ja esta autenticado, usando token
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        //return super.attemptAuthentication(request, response)
        val(username,password)   =   ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username,password)
        println("attemptAuthentication")
        println("$token")
        return authManager.authenticate(token)
    }

    //valida se o usuario esta autenticado
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val user = (authResult?.principal as UserDetails)//o principal é o usuraio logado do metodo UsernamePasswordAuthenticatioYoken
        val token = jwtUtil.generateToken(user.username,user.authorities)
        println("SuccesfulAuthentication")
        println(token)
        response?.addHeader("Authorization","Bearer $token")
    }
}
