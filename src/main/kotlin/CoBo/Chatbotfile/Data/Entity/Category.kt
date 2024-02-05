package CoBo.Chatbotfile.Data.Entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.SQLDelete

@Entity
@SQLDelete(sql = "UPDATE category SET deleted = true WHERE name = ?")
data class Category(
    @Id
    var name: String,
    var file_count: Int,
    var deleted: Boolean

)