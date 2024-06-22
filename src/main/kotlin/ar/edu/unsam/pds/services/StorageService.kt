package ar.edu.unsam.pds.services



import ar.edu.unsam.pds.exceptions.InternalServerError
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

@Service
class StorageService {
    private val basePath: Path = Path.of("media").toAbsolutePath()
    private val privatePath: Path = Path.of("media/private").toAbsolutePath()
    private val publicPath: Path = Path.of("media/public").toAbsolutePath()

    private fun delete(path: Path?) {
        try {
            this.deleteIfExists(path)
        } catch (ex: IOException) {
            throw InternalServerError("Storage Service Error")
        }
    }

    private fun save(oldPath: Path?, newPath: Path?, file: MultipartFile?): Path {
        val safeName: Path
        try {
            if (newPath == null || file == null) {
                throw IOException()
            }

            if (oldPath != null && newPath != oldPath) {
                this.deleteIfExists(oldPath)
            }

            safeName = safeName(newPath)
            file.transferTo(safeName)

            return safeName
        } catch (ex: IOException) {
            throw InternalServerError("Storage Service Error")
        }
    }

    // *****************************************************************************************************************
    fun privateDelete(name: String?) {
        val name_ = this.getFileName(privatePath, name)
        this.delete(name_)
    }

    fun privateSave(oldName: String?, newName: String?, file: MultipartFile?): String {
        val oldName_ = this.getFileName(privatePath, oldName)
        val newName_ = this.getFileName(privatePath, newName)

        return this.relativeze(this.save(oldName_, newName_, file))
    }

    fun publicDelete(name: String?) {
        val name_ = this.getFileName(publicPath, name)
        this.delete(name_)
    }

    fun publicSave(oldName: String?, newName: String?, file: MultipartFile?): String {
        val oldName_ = this.getFileName(publicPath, oldName)
        val newName_ = this.getFileName(publicPath, newName)

        return this.relativeze(this.save(oldName_, newName_, file))
    }

    // *****************************************************************************************************************
    //##################################################################################################################
    private fun getFileName(path: Path, name: String?): Path? {
        return if ((name != null)) path.resolve(Path.of(name).fileName) else null
    }

    private fun relativeze(path: Path): String {
        return basePath.relativize(path).toString()
    }

    @Throws(IOException::class)
    private fun deleteIfExists(path: Path?) {
        if (path != null && Files.isRegularFile(path)) Files.deleteIfExists(path)
    }

    private fun changePrefix(path: Path): Path {
        val name = path.fileName.toString()
        val random = Math.random().toString().substring(2, 10) + "_"

        return path.parent.resolve(random + name)
    }

    @Throws(IOException::class)
    private fun safeName(path: Path): Path {
        var attempts = 0
        var filePath = path

        while (Files.exists(filePath) && attempts < 11) {
            attempts = attempts + 1
            filePath = changePrefix(filePath)
        }

        if (attempts == 11) throw IOException()

        return filePath
    }

    companion object {
        //##################################################################################################################
        fun readFile(FILE_NAME: String?): String {
            try {
                val path = Path.of(FILE_NAME).toAbsolutePath()
                return Files.readString(path)
            } catch (e: IOException) {
                throw RuntimeException("oops, there is a problem with the file!!!")
            }
        }
    }
}
