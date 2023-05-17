package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
        private val userDetailService: UserDetailsService,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf().disable()///remoção do controle de csrf, Verificar como adicionar essa etapa, sem ela bloqueava o post
                .authorizeHttpRequests()
                //antMatchers was deprecated. HOw solve https://docs.spring.io/spring-security/reference/5.8/migration/servlet/config.html
                .requestMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
                .anyRequest().
                authenticated()
                .and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .httpBasic()
        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}