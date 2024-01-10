package CoBo.Chatbotfile.Data.Dto.File.Req

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile


data class FilePostReq(
    @Schema(description = "글의 제목", example = "11월 11일 강의자료입니다.")
    var name: String,
    @Schema(description = "업로드 할 파일", example = "킹승규의 강의자료.pdf")
    var multipartFile: MultipartFile
)
