package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
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

    @PatchMapping
    @Operation(summary = "카테고리명 변경 API", description = "해당 카테고리명을 수정")
    @Parameters(
        Parameter(name = "oldCategory", description = "변경 전의 카테고리 명"),
        Parameter(name = "newCategory", description = "변경 할 카테고리 명")
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "409", description = "기존의 데이터와 충돌합니다.", content = arrayOf(Content()))
    )
    fun patch(@RequestParam oldCategory: String, @RequestParam newCategory: String): ResponseEntity<HttpStatus>{
        return categoryService.patch(oldCategory, newCategory)
    }
}