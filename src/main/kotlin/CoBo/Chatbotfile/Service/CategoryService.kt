package CoBo.Chatbotfile.Service

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import org.springframework.http.ResponseEntity

interface CategoryService {
    fun getAll(): ResponseEntity<List<CategoryGetAllRes>>
}