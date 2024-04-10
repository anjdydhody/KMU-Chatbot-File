package CoBo.Chatbotfile.Repository

import CoBo.Chatbotfile.Data.Entity.File
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileRepository: JpaRepository<File, Int> {
    fun countAllByDeleted(deleted: Boolean): Long
    fun findAllByDeleted(deleted: Boolean): List<File>
}