package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.nio.file.Files
import java.nio.file.Path


@Service
class StorageService {
    @Autowired private lateinit var environment: Environment

    private val basePath: Path = Path.of("media").toAbsolutePath()
    private val privatePath: Path = Path.of("media/private").toAbsolutePath()
    private val publicPath: Path = Path.of("media/public").toAbsolutePath()

    init {
        // Crear directorios si no existen
        if (!Files.exists(basePath)) {
            Files.createDirectories(basePath)
        }
        if (!Files.exists(privatePath)) {
            Files.createDirectories(privatePath)
        }
        if (!Files.exists(publicPath)) {
            Files.createDirectories(publicPath)
        }
    }

    public fun defaultImage() = "${this.baseUrl()}/private/default.png"

    fun baseUrl() = "http://${this.getDomain()}:8080/media"

    fun getDomain() =
        if (environment.acceptsProfiles(Profiles.of("prod"))) "149.50.141.196"
        else "localhost"

    //public path
    fun deletePublic(imageName: String) =  deleteImage(publicPath, imageName)
    fun savePublic(file: MultipartFile) = saveImage(publicPath, file)
    fun updatePublic(oldImageName: String, newImageFile: MultipartFile) = updateImage(publicPath, oldImageName, newImageFile)

    //private path
    fun deletePrivate(imageName: String)=  deleteImage(privatePath, imageName)
    fun savePrivate(file: MultipartFile) = saveImage(privatePath, file)
    fun updatePrivate(oldImageName: String, newImageFile: MultipartFile) = updateImage(privatePath, oldImageName, newImageFile)


    // Eliminar imagen
    private fun deleteImage(directory: Path, imageName: String) {
        val imagePath = directory.resolve(imageName.substringAfterLast("/"))
        if (Files.exists(imagePath)) {
            Files.delete(imagePath)
        } else {
            throw InternalServerError("la imagen no existe")
        }
    }

    // Guardar imagen en el directorio especificado (public/private)
    private fun saveImage(directory: Path, file: MultipartFile?): String {

        if(file == null) throw ValidationException("la imagen es requerida")

        val imageName = "${System.currentTimeMillis()}_${file.originalFilename}"
        val destFile = directory.resolve(imageName).toFile()
        file.transferTo(destFile)
        return "${this.baseUrl()}/${directory.fileName}/$imageName"
    }

    // Actualizar imagen
    private fun updateImage(directory: Path, oldImageName: String, newImageFile: MultipartFile?): String {

        if(newImageFile == null) throw ValidationException("la imagen es requerida")
        if(oldImageName != this.defaultImage()){
            deleteImage(directory, oldImageName)
        }
        return saveImage(directory, newImageFile)
    }

}