package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes
import CoBo.Chatbotfile.Service.FileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/file")
@Tag(name = "파일 관련 API")
@RequiredArgsConstructor
class FileController(private val fileService: FileService) {


    @GetMapping("/{fileId}")
    @Operation(summary = "파일 다운로드 API", description = "다운로드 할 파일의 FileId 입력")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "인증 실패", content = arrayOf(Content())),
        ApiResponse(responseCode = "404", description = "파일을 찾을 수 없음", content = arrayOf(Content()))
    )
    fun get(@PathVariable("fileId") fileId: Int): ResponseEntity<Resource>{
        return fileService.get(fileId)
    }

    @GetMapping("/list")
    @Operation(summary = "파일리스트 조회 API")
    @Parameters(
        Parameter(name = "category", description = "검색할 카테고리")
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = FileGetListElementRes::class)))),
        ApiResponse(responseCode = "400", description = "잘못된 파라미터 전달", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "인증 실패", content = arrayOf(Content()))
    )
    fun getList(@RequestParam category: String):ResponseEntity<List<FileGetListElementRes>>{
        return fileService.getList(category)
    }

    @GetMapping("/all")
    @Operation(summary = "파일리스트 조회 API", description = "카테고리 없음")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = FileGetListElementRes::class)))),
        ApiResponse(responseCode = "400", description = "잘못된 파라미터 전달", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "인증 실패", content = arrayOf(Content()))
    )
    fun getAll():ResponseEntity<List<FileGetListElementRes>>{
        return fileService.getAll()
    }
}