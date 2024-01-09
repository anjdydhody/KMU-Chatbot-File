package CoBo.Chatbotfile.Data.Dto.File.Res

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class FileGetListElementRes(
    @Schema(description = "해당 파일의 ID", example = "13")
    val id: Int?,
    @Schema(description = "파일의 이름", example = "소머리국밥.jpg")
    val name: String,
    @Schema(description = "파일의 사이즈", example = "312445")
    val size: Long,
    @Schema(description = "파일의 업로드 날짜", example = "2024-01-09T23:03:23.322177")
    val created_at: LocalDateTime
)
