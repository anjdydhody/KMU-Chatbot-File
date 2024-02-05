package CoBo.Chatbotfile.Repository

import CoBo.Chatbotfile.Repository.Custom.CategoryRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<CategoryRepository, String>, CategoryRepositoryCustom {
}