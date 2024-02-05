package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.CategoryService
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/category")
@Tag(name = "카테고리 관련 API(교수, 개발자만 사용 가능)")
@RequiredArgsConstructor
class AuthCategoryController(
    private val categoryService: CategoryService
) {
}