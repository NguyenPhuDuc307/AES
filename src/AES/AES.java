package AES;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static String getKEY(String FileURL){
        String KEY = null;
        try {
            StringBuffer sb;
            try (FileReader fr = new FileReader(new File(FileURL))) {
                sb = new StringBuffer();
                char ca[] = new char[5] ;
                while (fr.ready()) {
                    int len = fr.read(ca);
                    sb.append(ca,0,len) ;
                }
            }
            KEY = sb.toString();
        } catch (IOException e) {
        }
        return KEY;
    }
    
    public static String encrypt(String string) {
        try {
            String KEY = getKEY("KEY.txt");
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
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static String decrypt(String string) {
        try {
            String KEY = getKEY("KEY.txt");
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
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
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
        AES aes = new AES();
        String enString = aes.encrypt(string);
        String deString = aes.decrypt("dMIbvRe6rWhgWMexX8nbcQ==");
        System.out.println(enString);
        System.out.println(deString);
    }
}
