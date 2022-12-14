package co.edu.escuelaing;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        port(getPort());
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("keystores/ecikeystore.p12", "123456", null, null);
        SecureURLReader.setKey("ecitruststore.p12","654321");
        get("/hellolocal", (req, res) -> "Hello World Server 5000");
        get("/helloremoto", (req, res) -> SecureURLReader.readURL("https://localhost:5001/hellolocal"));

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}