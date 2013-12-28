package fractals

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes

object GoogleDrive {

  /** Email of the Service Account */
  val SERVICE_ACCOUNT_EMAIL = "930057263021-stave1gsmbkr0mu8uasts9au1q8feale@developer.gserviceaccount.com";

  /** Path to the Service Account's Private Key file */
  val SERVICE_ACCOUNT_PKCS12_FILE_PATH =
    "faa5ab20d802867c17c890b72a385b6f7cc1ba89-privatekey.p12"

  /**
   * Build and returns a Drive service object authorized with the service accounts.
   *
   * @return Drive service object that is ready to make requests.
   */
  def getDriveService = {
    val httpTransport = new NetHttpTransport();
    val jsonFactory = new JacksonFactory();
    val scope = new java.util.HashSet[String]()
    scope.add(DriveScopes.DRIVE)

    val credential = new GoogleCredential.Builder()
      .setTransport(httpTransport)
      .setJsonFactory(jsonFactory)
      .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
      .setServiceAccountScopes(scope)
      .setServiceAccountPrivateKeyFromP12File(
        new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
      .build();
    val service = new Drive.Builder(httpTransport, jsonFactory, null)
      .setHttpRequestInitializer(credential).build();
    service;
  }
}