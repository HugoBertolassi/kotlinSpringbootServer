package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService (
     private val javaMailSender: JavaMailSender
){
    fun notificar(autorEmail:String){
        val message = SimpleMailMessage()

        message.setSubject("[Alura] Resposta do Topico Recebida")
        message.setText("Ola seu topico foi respondido")
        message.setTo(autorEmail)

        javaMailSender.send(message)
    }
}