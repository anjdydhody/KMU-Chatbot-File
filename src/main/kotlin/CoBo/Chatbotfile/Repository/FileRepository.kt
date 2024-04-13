package CoBo.Chatbotfile.Repository

import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes
import CoBo.Chatbotfile.Data.Entity.File
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FileRepository: JpaRepository<File, Int> {
    @Query("SELECT new CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes(f.id, f.name, f.fileName, f.size, f.createdAt, f.category.name) " +
            "FROM File f INNER JOIN f.category c WHERE f.deleted = false AND f.category.name = :category " +
            "ORDER BY f.id DESC")
    fun findFileGetListElementResAllByCategory(category: String):List<FileGetListElementRes>
    @Query("SELECT new CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes(f.id, f.name, f.fileName, f.size, f.createdAt, f.category.name) " +
            "FROM File f WHERE f.deleted = false " +
            "ORDER BY f.id DESC")
    fun findFileGetListElementResAll(): List<FileGetListElementRes>
}