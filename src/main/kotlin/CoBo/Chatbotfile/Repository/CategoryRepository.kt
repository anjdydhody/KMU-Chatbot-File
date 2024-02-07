package CoBo.Chatbotfile.Repository

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import CoBo.Chatbotfile.Data.Entity.Category
import CoBo.Chatbotfile.Repository.Custom.CategoryRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository: JpaRepository<Category, String>, CategoryRepositoryCustom {

    @Query("SELECT new CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes(c.name, c.count) " +
            "FROM Category c WHERE c.deleted = false")
    fun findCategoryGetAllResAllBy():List<CategoryGetAllRes>
    fun deleteByNameAndCount(name: String, file_count: Int): Long
}