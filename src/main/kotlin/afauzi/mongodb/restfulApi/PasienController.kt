package afauzi.mongodb.restfulApi

import afauzi.mongodb.restfulApi.data.Pasien
import afauzi.mongodb.restfulApi.data.PasienRepository
import afauzi.mongodb.restfulApi.request.PasienRequest
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/pasien") // Endpoint
class PasienController(private val pasienRepository: PasienRepository) {
    @GetMapping
    fun getAllPatients(): ResponseEntity<List<Pasien>> {
        val pasien = pasienRepository.findAll()
        return ResponseEntity.ok(pasien)
    }
    @GetMapping("/{id}")
    fun getOnePasien(@PathVariable("id") id: String): ResponseEntity<Pasien> {
        val pasien = pasienRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(pasien)
    }
    @PostMapping
    fun createPatient(@RequestBody request: PasienRequest): ResponseEntity<Pasien> {
        val pasien = pasienRepository.save(Pasien(
            name = request.name,
            description = request.description
        ))
        return ResponseEntity(pasien, HttpStatus.CREATED)
    }
    @PutMapping("/{id}")
    fun updatePasien(@RequestBody request: PasienRequest, @PathVariable("id") id: String): ResponseEntity<Pasien> {
        val pasien = pasienRepository.findOneById(ObjectId(id))
        val updatedPasien = pasienRepository.save(Pasien(
            id = pasien.id,
            name = request.name,
            description = request.description,
            createdDate = pasien.createdDate,
            modifiedDate = LocalDateTime.now()
        ))
        return ResponseEntity.ok(updatedPasien)
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable("id") id: String): ResponseEntity<Unit> {
        pasienRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}