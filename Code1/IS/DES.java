import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DES {
    public static void main(String[] args) throws Exception {
        String key = "squirrel123"; // needs to be at least 8 characters for DES

        String plainText = "Hello, World!";
        System.out.println("Plain Text : " + plainText);

        String encryptedText = encrypt(key, plainText);
        System.out.println("Encrypted Text : " + encryptedText);

        String decryptedText = decrypt(key, encryptedText);
        System.out.println("Decrypted Text : " + decryptedText);
    }

    public static String encrypt(String key, String message) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key.getBytes()));

        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] textEncrypted = desCipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(textEncrypted);
    }

    public static String decrypt(String key, String message) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(message);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key.getBytes()));

        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] textDecrypted = desCipher.doFinal(bytes);

        return new String(textDecrypted);
    }
}


/*
---------------------------------------------------------------------------------------
Output
---------------------------------------------------------------------------------------
Plain Text : Hello, World!
Encrypted Text : EJgqB6Cf5lf79AND9a1CBw==
Decrypted Text : Hello, World!
*/


// What is DES?
// DES is a symmetric-key encryption algorithm — the same key is used for both encryption and decryption.

// It operates on 64-bit (8-byte) blocks of data using a 56-bit key.

// It’s not considered secure anymore, but is still useful for learning purposes.

// ✅ Code Explanation
// 1. Main Method
// java
// Copy
// Edit
// String key = "squirrel123";
// String plainText = "Hello, World!";
// key is a string (used to generate a DES key). Only the first 8 bytes (squirrel) are used.

// plainText is the message to encrypt.

// 2. Encrypt Method
// java
// Copy
// Edit
// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
// SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key.getBytes()));
// Converts the string key into a valid DES key using DESKeySpec.

// java
// Copy
// Edit
// Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
// desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
// Initializes a Cipher object for encryption using:

// DES algorithm

// ECB (Electronic Code Book) mode (simple block-by-block encryption)

// PKCS5Padding to handle data that doesn’t fit evenly into 8-byte blocks.

// java
// Copy
// Edit
// byte[] textEncrypted = desCipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
// Encrypts the plaintext and returns a byte array.

// java
// Copy
// Edit
// return Base64.getEncoder().encodeToString(textEncrypted);
// Converts the encrypted byte array to a readable Base64 string.

// 3. Decrypt Method
// java
// Copy
// Edit
// byte[] bytes = Base64.getDecoder().decode(message);
// Decodes the Base64-encoded ciphertext back to bytes.

// The rest of the code mirrors encryption, but:

// java
// Copy
// Edit
// desCipher.init(Cipher.DECRYPT_MODE, secretKey);
// Initializes the cipher for decryption.

// java
// Copy
// Edit
// byte[] textDecrypted = desCipher.doFinal(bytes);
// Decrypts the ciphertext.

// java
// Copy
// Edit
// return new String(textDecrypted);
// Converts decrypted bytes back to a string.

// 🧪 Sample Output
// pgsql
// Copy
// Edit
// Plain Text : Hello, World!
// Encrypted Text : EJgqB6Cf5lf79AND9a1CBw==
// Decrypted Text : Hello, World!
// The encryption hides the original message.

// The decryption successfully restores it using the same key.

// ⚠️ Security Note:
// DES is outdated and insecure by modern standards.

// Use AES (Advanced Encryption Standard) for secure applications.