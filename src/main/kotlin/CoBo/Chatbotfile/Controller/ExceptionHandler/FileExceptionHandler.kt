package CoBo.Chatbotfile.Controller.ExceptionHandler

import CoBo.Chatbotfile.Controller.FileController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [FileController::class])
class FileExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun fileNoSuchElementExceptionHandler(): ResponseEntity<String>{
        return ResponseEntity("파일을 찾을 수 없음", HttpStatus.NOT_FOUND)
    }
}