package afauzi.mongodb.restfulApi.data

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PasienRepository: MongoRepository<Pasien, String> {
    fun findOneById(id: ObjectId): Pasien
    override fun deleteAll()
}