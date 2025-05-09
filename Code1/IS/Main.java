 import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "Anurag";
        System.out.println("Plain Text : " + plainText);

        String encryptedText = encrypt(publicKey, plainText);
        System.out.println("Encrypted Text : " + encryptedText);

        String decryptedText = decrypt(privateKey, encryptedText);
        System.out.println("Decrypted Text : " + decryptedText);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static String encrypt(PublicKey key, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
    }

    public static String decrypt(PrivateKey key, String encrypted) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(encrypted);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(bytes), "UTF-8");
    }
}


/*
-----------------------------------------------------------
Output
----------------------------------------------------------
Plain Text : Hello, World!

Encrypted Text : kPsmmJQuHckF71TrRdVBLQT6cu9vQkNeV4UsLYQ2G99uUscNoRcbSEXmIcChBA10mpL3WhCzQh010MDyS+OiZVmaJR6lFNN5aN25JkVAv8n9AxoEFJsS1VL4472QhRSMlK2A+FPPtZW0Y1FToc96xyjRVLB78FgU5IyB7MqqC3AYrQlYQ+sQ409BkZDebVZQrs36hjJLa4u70o+d3tR8bE0StI2yqc2qoGO9KwjVIgjgAveE0YfAzASgOoj/K5uYgqmQkJs58ni7M0w2tfV17bmq8BcCaty6tv+6uw6GPnKyTG/zXnx+UC4hUYdKOkzNWLUzNCrXj9G3Oo4IJDCm6w==

Decrypted Text : Hello, World!

*/


// What is RSA?
// RSA is an asymmetric encryption technique. It uses a pair of keys:

// Public Key → for encryption (shared with others)

// Private Key → for decryption (kept secret)

// ✅ Step-by-Step Explanation
// 1. Generate Key Pair
// java
// Copy
// Edit
// KeyPair keyPair = generateKeyPair();
// This calls the method generateKeyPair(), which creates a public and private RSA key pair using KeyPairGenerator.

// java
// Copy
// Edit
// KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
// generator.initialize(2048); // 2048-bit key
// 2048 is the key size, a common secure value.

// 2. Encryption
// java
// Copy
// Edit
// String encryptedText = encrypt(publicKey, plainText);
// The encrypt() method:

// java
// Copy
// Edit
// Cipher cipher = Cipher.getInstance("RSA");
// cipher.init(Cipher.ENCRYPT_MODE, key);
// return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
// Uses the public key to initialize the cipher in encryption mode.

// Encrypts the message (plainText).

// Converts the encrypted byte array to a readable Base64 string.

// 3. Decryption
// java
// Copy
// Edit
// String decryptedText = decrypt(privateKey, encryptedText);
// The decrypt() method:

// java
// Copy
// Edit
// byte[] bytes = Base64.getDecoder().decode(encrypted);
// Cipher cipher = Cipher.getInstance("RSA");
// cipher.init(Cipher.DECRYPT_MODE, key);
// return new String(cipher.doFinal(bytes), "UTF-8");
// Decodes the Base64-encoded encrypted string.

// Initializes the cipher in decryption mode using the private key.

// Decrypts and returns the original message.

// 🧪 Example Output:
// Assume the plain text is "Anurag".

// plaintext
// Copy
// Edit
// Plain Text : Anurag
// Encrypted Text : (long base64 string)
// Decrypted Text : Anurag
// Even though the encrypted text is unreadable, it can be decrypted back to "Anurag" only with the correct private key.

// 🔒 Why Use RSA?
// Public-key encryption allows secure communication over insecure channels.

// The public key can be shared freely, but only the holder of the private key can decrypt the messages.