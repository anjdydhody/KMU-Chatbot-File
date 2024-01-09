package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Data.Entity.File
import CoBo.Chatbotfile.Repository.FileRepository
import CoBo.Chatbotfile.Service.FileService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Service
@RequiredArgsConstructor
@Slf4j
class FileServiceImpl(
    @Value("\${file.path}")
    private val filePath: String,
    private val fileRepository: FileRepository):FileService {

    override fun post(multipartFile: MultipartFile): ResponseEntity<HttpStatus> {
        val originalName = multipartFile.originalFilename

        val extension = StringUtils.getFilenameExtension(originalName)
        val newName = filePath + UUID.randomUUID() + "." + extension
        val filePath = Paths.get(newName)

        val file = File(
            id = null,
            name = originalName,
            path = newName,
            size = multipartFile.size)

        Files.copy(multipartFile.inputStream, filePath)

        fileRepository.save(file)

        return ResponseEntity(HttpStatus.OK)
    }
}