package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice  //permite o controle das mensagens do rest
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
            exception: NotFoundException,
            request:HttpServletRequest//retorna o camnio da requisição
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = exception.message,
                timestamp = LocalDateTime.now(),
                path=request.servletPath

        )

    }
    @ExceptionHandler(MethodArgumentNotValidException::class)//essa é exccepton enviada pelo string
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
            exception: MethodArgumentNotValidException,
            request:HttpServletRequest//retorna o camnio da requisição
    ): ErrorView {
        val errorMessage=HashMap<String,String?>()
        exception.bindingResult.fieldErrors. forEach{
            e -> errorMessage.put(e.field,e.defaultMessage)
        }//retorna lista com os erros
        return ErrorView(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                message = errorMessage.toString(),
                timestamp = LocalDateTime.now(),
                path=request.servletPath

        )

    }

    //configuração para errros não tratados,exception generica
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
            exception: Exception,
            request:HttpServletRequest//retorna o camnio da requisição
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = exception.message,
                timestamp = LocalDateTime.now(),
                path=request.servletPath

        )

    }
}