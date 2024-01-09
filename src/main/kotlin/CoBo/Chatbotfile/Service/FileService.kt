package CoBo.Chatbotfile.Service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun post(multipartFile: MultipartFile): ResponseEntity<HttpStatus>
}