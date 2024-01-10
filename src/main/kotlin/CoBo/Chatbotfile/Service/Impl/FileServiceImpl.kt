package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes
import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListRes
import CoBo.Chatbotfile.Data.Entity.File
import CoBo.Chatbotfile.Repository.FileRepository
import CoBo.Chatbotfile.Service.FileService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.*
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

    override fun post(fileName: String, multipartFile: MultipartFile): ResponseEntity<HttpStatus> {
        val originalName = multipartFile.originalFilename

        val extension = StringUtils.getFilenameExtension(originalName)
        val newName = filePath + UUID.randomUUID() + "." + extension
        val filePath = Paths.get(newName)

        val file = File(
            id = null,
            name = fileName,
            fileName = originalName ?: newName,
            path = newName,
            size = multipartFile.size,
            deleted = false)

        Files.copy(multipartFile.inputStream, filePath)

        fileRepository.save(file)

        return ResponseEntity(HttpStatus.OK)
    }

    override fun get(fileId: Int): ResponseEntity<Resource> {

        val optionalFile = fileRepository.findById(fileId)

        if(optionalFile.isEmpty || optionalFile.get().deleted)
            throw NoSuchElementException()

        val file = java.io.File(optionalFile.get().path)

        val fileContent = Files.readAllBytes(file.toPath())

        val resource = ByteArrayResource(fileContent)
        val encodedFileName = URLEncoder.encode(optionalFile.get().fileName, StandardCharsets.UTF_8.toString())

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$encodedFileName\"")
            .body(resource)
    }

    override fun getList(page: Int, page_size: Int): ResponseEntity<FileGetListRes> {

        val fileGetListElementResList = ArrayList<FileGetListElementRes>()

        for (file in fileRepository.findAllByDeleted(false, PageRequest.of(page, page_size, Sort.by("id").descending())))
            fileGetListElementResList.add(FileGetListElementRes(id = file.id, name = file.name, size = file.size, created_at = file.createdAt, fileName = file.fileName))

        return ResponseEntity(FileGetListRes(
            fileCount = fileRepository.countAllByDeleted(false), fileGetListElementResList = fileGetListElementResList
        ), HttpStatus.OK)
    }

    override fun delete(fileIdList: List<Int>): ResponseEntity<HttpStatus> {

        fileRepository.deleteAllById(fileIdList)

        return ResponseEntity(HttpStatus.OK)
    }

    override fun patch(fileId: Int, name: String): ResponseEntity<HttpStatus> {
        val file = fileRepository.findById(fileId)

        if(file.isEmpty || file.get().deleted)
            throw NoSuchElementException()

        file.get().name = name
        fileRepository.save(file.get())

        return ResponseEntity(HttpStatus.OK)
    }
}