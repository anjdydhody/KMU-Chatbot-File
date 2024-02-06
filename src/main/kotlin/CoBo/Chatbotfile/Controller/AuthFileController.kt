package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.FileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/auth/file")
@Tag(name = "파일 관련 API(교수, 개발자만 사용 가능)")
@RequiredArgsConstructor
class AuthFileController(
    private val fileService: FileService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "파일 업로드 API", description = "교수, 개발자 권한만 사용 가능")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "권한이 없습니다.", content = arrayOf(Content())),
        ApiResponse(responseCode = "503", description = "파일을 업로드하는 과정에서 에러가 발생했습니다.", content = arrayOf(Content()))
    )
    fun post(@RequestParam fileName: String, @RequestParam category: String, @RequestPart multipartFile: MultipartFile): ResponseEntity<HttpStatus> {
        return fileService.post(fileName, category, multipartFile)
    }

    @DeleteMapping
    @Operation(summary = "파일 삭제 API")
    @Parameters(
        Parameter(name = "fileIdList", description = "삭제할 파일들의 Id 리스트", example = "[1, 2, 3]")
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
        ApiResponse(responseCode = "403", description = "인증 실패", content = arrayOf(Content()))
    )
    fun delete(@RequestParam fileIdList:List<Int>):ResponseEntity<HttpStatus>{
        return fileService.delete(fileIdList)
    }

    @PatchMapping
    @Operation(summary = "파일 수정 API")
    @Parameters(
        Parameter(name = "fileId", description = "수정할 파일의 Id", example = "27"),
        Parameter(name = "name", description = "수정할 이름", example = "킹승규의 비밀노트")
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content())),
    )
    fun patch(@RequestParam fileId: Int, @RequestParam name: String): ResponseEntity<HttpStatus>{
        return fileService.patch(fileId, name)
    }
}