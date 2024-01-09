package CoBo.Chatbotfile.Data.Entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class File(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var name: String?,
    var path: String,
    var size: Long,
    var createdAt: LocalDateTime = LocalDateTime.now()
)
