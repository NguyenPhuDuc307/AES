package AES;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static String encrypt(String string, String KEY) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = KEY.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);

            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] byteEncrypted = cipher.doFinal(string.getBytes());
            String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);

            return encrypted;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static String decrypt(String string, String KEY) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = KEY.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);

            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(string));
            String decrypted = new String(byteDecrypted);
            return decrypted;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        String string = "String decompressGZIP(byte[] gzip) throws IOException {\n"
                + "    java.util.zip.Inflater inf = new java.util.zip.Inflater();\n"
                + "    java.io.ByteArrayInputStream bytein = new java.io.ByteArrayInputStream(gzip);\n"
                + "    java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(bytein);\n"
                + "    java.io.ByteArrayOutputStream byteout = new java.io.ByteArrayOutputStream();\n"
                + "    int res = 0;\n"
                + "    byte buf[] = new byte[1024];\n"
                + "    while (res >= 0) {\n"
                + "        res = gzin.read(buf, 0, buf.length);\n"
                + "        if (res > 0) {\n"
                + "            byteout.write(buf, 0, res);\n"
                + "        }\n"
                + "    }\n"
                + "    byte uncompressed[] = byteout.toByteArray();\n"
                + "    return (uncompressed.toString());\n"
                + "}";
        String KEY = "AVC";
        AES aes = new AES();
        String enString = aes.encrypt(string, KEY);
        String deString = aes.decrypt(enString, KEY);
        System.out.println(enString);
        System.out.println(deString);

    }
}
