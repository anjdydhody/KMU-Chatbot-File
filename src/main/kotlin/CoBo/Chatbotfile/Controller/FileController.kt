package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.FileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/file")
@Tag(name = "파일 관련 API")
@RequiredArgsConstructor
class FileController(private val fileService: FileService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "파일 업로드 API", description = "교수, 개발자 권한만 사용 가능")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "권한이 없습니다.", content = arrayOf(Content())),
        ApiResponse(responseCode = "503", description = "파일을 업로드하는 과정에서 에러가 발생했습니다.", content = arrayOf(Content()))
    )
    fun post(@RequestPart multipartFile: MultipartFile): ResponseEntity<HttpStatus>{
        return fileService.post(multipartFile)
    }
}