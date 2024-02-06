package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth/category")
@Tag(name = "카테고리 관련 API(교수, 개발자만 사용 가능)")
@RequiredArgsConstructor
class AuthCategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    @Operation(summary = "카테고리 추가 API", description = "해당 카테고리를 추가")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content()))
    )
    fun post(@RequestParam category: String): ResponseEntity<HttpStatus> {
        return categoryService.post(category)
    }
}