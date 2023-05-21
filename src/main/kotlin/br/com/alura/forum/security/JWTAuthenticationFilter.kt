package br.com.alura.forum.security

import br.com.alura.forum.config.JWTUtil
import jakarta.servlet.Filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(
        private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    private fun getTokenDetail(token: String?):String? {
       return token?.let{  //o let garante que so vai pegar se não for nulo
           jwt->
           //println("get token : $jwt")
           jwt.startsWith("Bearer ")
           jwt.substring(7, jwt.length)
       }
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
       val token = request.getHeader("Authorization")
        val jwt= getTokenDetail(token)//remover o Bearer do authorization
        if(jwtUtil.isValid(jwt)){
            //pegar se o usuario ainda esta logado
            println("get token : $jwt")
            val authentication= jwtUtil.getAuthentication(jwt)
            println(authentication)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

}
