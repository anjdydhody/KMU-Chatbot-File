package CoBo.Chatbotfile.Data.Dto.File.Res

import io.swagger.v3.oas.annotations.media.Schema

data class FileGetListRes(
    val fileGetListElementResList: List<FileGetListElementRes>
)