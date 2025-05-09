import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.Scanner;

public class MD5HashGenerator {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the text to hash: ");
            String input = scanner.nextLine();

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute the digest (returns byte array)
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation (positive number)
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashText = no.toString(16);

            // Pad with leading zeros to make it 32 characters
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            System.out.println("MD5 Digest: " + hashText);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

// What is a Hash?
// A hash function turns any input (like a password or message) into a fixed-size string of characters. MD5 always returns a 128-bit (16-byte) output, represented as 32 hexadecimal characters.

// ✅ Code Explanation (Line by Line)
// java
// Copy
// Edit
// Scanner scanner = new Scanner(System.in);
// System.out.print("Enter the text to hash: ");
// String input = scanner.nextLine();
// Prompts the user to enter any text.

// The input string is stored in the variable input.

// java
// Copy
// Edit
// MessageDigest md = MessageDigest.getInstance("MD5");
// Creates a MessageDigest object configured to use the MD5 algorithm.

// java
// Copy
// Edit
// byte[] messageDigest = md.digest(input.getBytes());
// Converts the string to bytes and computes its MD5 digest.

// Output is a byte array (length 16).

// java
// Copy
// Edit
// BigInteger no = new BigInteger(1, messageDigest);
// Converts the byte array to a positive BigInteger, making it easier to convert into hexadecimal.

// java
// Copy
// Edit
// String hashText = no.toString(16);
// Converts the BigInteger into a hexadecimal string.

// java
// Copy
// Edit
// while (hashText.length() < 32) {
//     hashText = "0" + hashText;
// }
// Ensures the output is exactly 32 characters long by adding leading zeros if needed.

// java
// Copy
// Edit
// System.out.println("MD5 Digest: " + hashText);
// Displays the final MD5 hash of the input string.

// 🔐 Example
// Input:

// nginx
// Copy
// Edit
// HelloWorld
// Output:

// yaml
// Copy
// Edit
// MD5 Digest: fc5e038d38a57032085441e7fe7010b0
// ⚠️ Note:
// MD5 is not secure for cryptographic purposes (like password hashing or digital signatures). It's still fine for checksums or non-critical integrity checks.