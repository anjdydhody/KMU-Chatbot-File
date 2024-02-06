package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import CoBo.Chatbotfile.Repository.CategoryRepository
import CoBo.Chatbotfile.Service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
): CategoryService{

    override fun getAll(): ResponseEntity<List<CategoryGetAllRes>> {
        return ResponseEntity
            .ok()
            .body(categoryRepository.findCategoryGetAllResAllBy())
    }

    override fun post(category: String): ResponseEntity<HttpStatus> {
        categoryRepository.saveOrUpdate(category)
        return ResponseEntity(HttpStatus.OK)
    }
}