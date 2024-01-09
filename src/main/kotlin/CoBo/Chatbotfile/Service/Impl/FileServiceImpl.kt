package CoBo.Chatbotfile.Service.Impl

import CoBo.Chatbotfile.Repository.FileRepository
import CoBo.Chatbotfile.Service.FileService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@Slf4j
class FileServiceImpl(
    private val fileRepository: FileRepository):FileService {
}