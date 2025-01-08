

import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecretKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); // Tamaño de la clave
        SecretKey secretKey = keyGen.generateKey();
        String base64Secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Clave secreta en Base64: " + base64Secret);
    }
}