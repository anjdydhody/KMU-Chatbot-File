package CoBo.Chatbotfile.Repository.Impl

import CoBo.Chatbotfile.Repository.Custom.CategoryRepositoryCustom
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@RequiredArgsConstructor
class CategoryRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate): CategoryRepositoryCustom {
    @Transactional
    override fun saveOrUpdate(category: String) {
        val sql = "INSERT INTO category (name, count, deleted) VALUES (?, 0, false) " +
                "ON DUPLICATE KEY UPDATE deleted = false"
        jdbcTemplate.update(sql, category)
    }

    @Transactional
    override fun updateName(oldCategory: String, newCategory: String){
        val sql = "UPDATE category SET name = ? WHERE name = ?"
        if(jdbcTemplate.update(sql, newCategory, oldCategory) == 0)
            throw EntityNotFoundException("Category not found : $oldCategory")
    }
}