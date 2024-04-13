package CoBo.Chatbotfile.Service

import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListElementRes
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun post(fileName: String, category: String, multipartFile: MultipartFile): ResponseEntity<HttpStatus>
    fun get(fileId: Int): ResponseEntity<Resource>
    fun getList(category: String): ResponseEntity<List<FileGetListElementRes>>
    fun getAll(): ResponseEntity<List<FileGetListElementRes>>
    fun delete(fileIdList:List<Int>): ResponseEntity<HttpStatus>
    fun patch(fileId: Int, name: String): ResponseEntity<HttpStatus>
}