package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import CoBo.Chatbotfile.Repository.CategoryRepository
import CoBo.Chatbotfile.Service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    override fun patch(oldCategory: String, newCategory: String): ResponseEntity<HttpStatus> {
        categoryRepository.updateName(oldCategory, newCategory)
        return ResponseEntity(HttpStatus.OK)
    }

    @Transactional
    override fun delete(category: String): ResponseEntity<HttpStatus> {
        return if(categoryRepository.deleteByNameAndCount(category, 0) > 0)
            ResponseEntity(HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}