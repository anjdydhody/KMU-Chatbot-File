package CoBo.Chatbotfile.Controller.ExceptionHandler

import CoBo.Chatbotfile.Controller.AuthCategoryController
import CoBo.Chatbotfile.Controller.CategoryController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [AuthCategoryController::class, CategoryController::class])
class CategoryExceptionHandler {

}