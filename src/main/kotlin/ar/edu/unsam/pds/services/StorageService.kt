package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.nio.file.Files
import java.nio.file.Path

@Service
class StorageService {

    @Value("\${images.base}")
    private lateinit var baseUrl: String

    @Value("\${images.default}")
    lateinit var defaultImage: String

    private val basePath: Path = Path.of("media").toAbsolutePath()
    private val privatePath: Path = Path.of("media/private").toAbsolutePath()
    private val publicPath: Path = Path.of("media/public").toAbsolutePath()

    init {
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

    fun publicPath(): String {
        return "$baseUrl/public"
    }

    fun privatePath(): String {
        return "$baseUrl/private"
    }

    fun deletePublic(imageName: String) {
        deleteImage(publicPath, imageName)
    }

    fun savePublic(file: MultipartFile): String {
        return saveImage(publicPath, file)
    }

    fun updatePublic(oldImageName: String, newImageFile: MultipartFile): String {
        return updateImage(publicPath, oldImageName, newImageFile)
    }

    fun deletePrivate(imageName: String) {
        deleteImage(privatePath, imageName)
    }

    fun savePrivate(file: MultipartFile): String {
        return saveImage(privatePath, file)
    }

    fun updatePrivate(oldImageName: String, newImageFile: MultipartFile): String {
        return updateImage(privatePath, oldImageName, newImageFile)
    }

    // #################################################################################################################
    private fun deleteImage(directory: Path, imageName: String) {
        val imagePath = directory.resolve(imageName.substringAfterLast("/"))

        if (Files.exists(imagePath)) {
            Files.delete(imagePath)
        } else {
            throw InternalServerError("la imagen no existe")
        }
    }

    private fun saveImage(directory: Path, file: MultipartFile?): String {
        if(file == null) {
            throw ValidationException("la imagen es requerida")
        }

        val imageName = "${System.currentTimeMillis()}_${file.originalFilename}"
        val destFile = directory.resolve(imageName).toFile()
        file.transferTo(destFile)

        return "${baseUrl}/${directory.fileName}/$imageName"
    }

    private fun updateImage(directory: Path, oldImageName: String, newImageFile: MultipartFile?): String {
        if(newImageFile == null) {
            throw ValidationException("la imagen es requerida")
        }

        if(oldImageName != defaultImage){
            deleteImage(directory, oldImageName)
        }

        return saveImage(directory, newImageFile)
    }
}