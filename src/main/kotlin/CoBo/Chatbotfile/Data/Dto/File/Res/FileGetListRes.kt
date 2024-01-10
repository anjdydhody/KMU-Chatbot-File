package CoBo.Chatbotfile.Data.Dto.File.Res

import io.swagger.v3.oas.annotations.media.Schema

data class FileGetListRes(
    @Schema(description = "파일의 전체 개수", example = "37")
    val fileCount: Long,
    val fileGetListElementResList: List<FileGetListElementRes>
)