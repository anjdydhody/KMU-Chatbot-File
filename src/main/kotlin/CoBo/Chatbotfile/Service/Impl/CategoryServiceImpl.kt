package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Repository.CategoryRepository
import CoBo.Chatbotfile.Service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
): CategoryService{
}