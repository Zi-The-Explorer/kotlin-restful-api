package afauzi.mongodb.restfulApi.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "pasien")
data class Pasien(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String? = null,
    val description: String? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
)
