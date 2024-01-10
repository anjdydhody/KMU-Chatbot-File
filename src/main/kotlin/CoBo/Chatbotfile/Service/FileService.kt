package CoBo.Chatbotfile.Service

import CoBo.Chatbotfile.Data.Dto.File.Res.FileGetListRes
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun post(multipartFile: MultipartFile): ResponseEntity<HttpStatus>
    fun get(fileId: Int): ResponseEntity<Resource>
    fun getList(page: Int, page_size: Int): ResponseEntity<FileGetListRes>
    fun delete(fileIdList:List<Int>): ResponseEntity<HttpStatus>
    fun patch(fileId: Int, name: String): ResponseEntity<HttpStatus>
}