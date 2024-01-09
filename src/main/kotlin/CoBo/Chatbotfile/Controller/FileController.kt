package CoBo.Chatbotfile.Controller

import CoBo.Chatbotfile.Service.FileService
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/file")
@Tag(name = "파일 관련 API")
@RequiredArgsConstructor
class FileController(private val fileService: FileService) {
}