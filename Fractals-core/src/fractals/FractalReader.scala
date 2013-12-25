package fractals

import java.io.ObjectInputStream
import java.io.InputStream
import com.google.api.client.http.GenericUrl

class FractalReader {
  val drive = GoogleDrive.getDriveService

  def getInputStream: InputStream = {
    val file = drive.files.list.execute.getItems().get(0)
    val resp = drive.getRequestFactory.buildGetRequest(new GenericUrl(file.getDownloadUrl)).execute

    resp.getContent
  }

  def getFractal: Fractal = {
    val in = new ObjectInputStream(getInputStream)

    in.readObject.asInstanceOf[Fractal]
  }
}