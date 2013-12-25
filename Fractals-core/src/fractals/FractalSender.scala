package fractals

import java.io.FileOutputStream
import java.io.ObjectOutputStream

import com.google.api.client.http.FileContent
import com.google.api.services.drive.model.File

class FractalSender {
  val drive = GoogleDrive.getDriveService
  val mime = "text/plain"

  def send(fractal: Fractal) = {
    val frac = new java.io.File("fractal")
    val out = new ObjectOutputStream(new FileOutputStream(frac))

    out.writeObject(fractal)

    out.close()

    val body = new File
    body.setTitle("fr")
    body.setMimeType(mime)

    val fileContent = new java.io.File("fractal")
    val mediaContent = new FileContent("text/plain", fileContent)

    val s = System.currentTimeMillis()

    val file = drive.files.insert(body, mediaContent).execute

    println("sent in " + (System.currentTimeMillis() - s))
    println(file)

  }
}
