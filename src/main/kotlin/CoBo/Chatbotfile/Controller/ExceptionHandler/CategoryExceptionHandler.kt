package CoBo.Chatbotfile.Controller.ExceptionHandler

import CoBo.Chatbotfile.Controller.AuthCategoryController
import CoBo.Chatbotfile.Controller.CategoryController
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException

@RestControllerAdvice(basePackageClasses = [AuthCategoryController::class, CategoryController::class])
class CategoryExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun categoryEntityNotFoundExceptionHandler(entityNotFoundException: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity(entityNotFoundException.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun categorySQLIntegrityConstraintViolationExceptionHandler(): ResponseEntity<String>{
        return ResponseEntity("기존의 데이터와 충돌합니다.", HttpStatus.CONFLICT)
    }
}