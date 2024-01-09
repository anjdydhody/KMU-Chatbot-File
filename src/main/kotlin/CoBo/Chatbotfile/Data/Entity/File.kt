package CoBo.Chatbotfile.Data.Entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class File(

    @Id
    var id: Int,
    var name: String,
    var path: String,
    var size: Int,
    var createdAt: LocalDateTime
)
