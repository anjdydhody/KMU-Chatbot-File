package CoBo.Chatbotfile.Repository

import CoBo.Chatbotfile.Data.Entity.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileRepository: JpaRepository<File, Int>
{
}