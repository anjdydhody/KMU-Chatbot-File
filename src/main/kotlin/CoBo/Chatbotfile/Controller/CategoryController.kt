package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import CoBo.Chatbotfile.Service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category")
@Tag(name = "카테고리 관련 API")
@RequiredArgsConstructor
class CategoryController(
    private val categoryService: CategoryService) {

    @GetMapping("/all")
    @Operation(summary = "전체 카테고리 조회 API", description = "카테고리와 해당 카테고리의 파일 개수를 반환")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content()))
    )
    fun getAll(): ResponseEntity<List<CategoryGetAllRes>>{
        return categoryService.getAll()
    }
}