package CoBo.Chatbotfile.Data.Dto.Category.Res

import io.swagger.v3.oas.annotations.media.Schema

data class CategoryGetAllRes(

    @Schema(description = "카테고리 명", example = "킹승규 카테고리")
    val name:String,

    @Schema(description = "해당 카테고리의 파일 개수", example = "13")
    val count: Int
)
