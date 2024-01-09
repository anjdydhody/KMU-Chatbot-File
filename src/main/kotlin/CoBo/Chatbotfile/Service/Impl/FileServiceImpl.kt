package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Data.Entity.File
import CoBo.Chatbotfile.Repository.FileRepository
import CoBo.Chatbotfile.Service.FileService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.NoSuchElementException
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
            size = multipartFile.size,
            isDel = false)

        Files.copy(multipartFile.inputStream, filePath)

        fileRepository.save(file)

        return ResponseEntity(HttpStatus.OK)
    }

    override fun getDownload(fileId: Int): ResponseEntity<Resource> {

        val optionalFile = fileRepository.findById(fileId)

        if(optionalFile.isEmpty || optionalFile.get().isDel)
            throw NoSuchElementException()

        val file = java.io.File(optionalFile.get().path)

        val fileContent = Files.readAllBytes(file.toPath())

        val resource = ByteArrayResource(fileContent)
        val encodedFileName = URLEncoder.encode(optionalFile.get().name, StandardCharsets.UTF_8.toString())

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$encodedFileName\"")
            .body(resource)
    }
}