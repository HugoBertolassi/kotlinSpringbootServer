package br.com.alura.forum.config

import br.com.alura.forum.security.JWTAuthenticationFilter
import br.com.alura.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
        private val configuration: AuthenticationConfiguration,
        private val userDetailService: UserDetailsService,
        private val jwtUtil: JWTUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf().disable()///remoção do controle de csrf, Verificar como adicionar essa etapa, sem ela bloqueava o post
                .authorizeHttpRequests().
                //antMatchers was deprecated. HOw solve https://docs.spring.io/spring-security/reference/5.8/migration/servlet/config.html
                requestMatchers(HttpMethod.POST,"/login").permitAll().
                requestMatchers("/h2-console").permitAll().
                requestMatchers("/swagger-ui/**").permitAll().
                requestMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll().
                requestMatchers("/topicos/**").hasAuthority("LEITURA_ESCRITA").
                anyRequest().
                authenticated().
                and()
                http.addFilterBefore(JWTLoginFilter(authManager= configuration.authenticationManager,jwtUtil=jwtUtil), UsernamePasswordAuthenticationFilter().javaClass) //essa etpa filtra a reuqisição para implementar a logica para gerar o token
                http.addFilterBefore(JWTAuthenticationFilter(jwtUtil=jwtUtil),UsernamePasswordAuthenticationFilter().javaClass)//verifica se o usuario esta logaddo
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //and()?.
                //formLogin()?.disable()?.
                //httpBasic() //removemos pq agora vamos utilizar token

        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    //Deprecated how solve:https://backendstory.com/spring-security-how-to-replace-websecurityconfigureradapter/
//    @Bean
//    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
//        return BCryptPasswordEncoder()
//    }

//    fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.userDetailsService(userDetailService)?.passwordEncoder(encoder())
//    }

}