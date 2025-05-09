import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {
    private final static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        BigInteger p = BigInteger.probablePrime(512, random);
        BigInteger g = new BigInteger("3");
        BigInteger aliceSecret = new BigInteger("123456789");
        BigInteger bobSecret = new BigInteger("987654321");

        // Alice computes her public key
        BigInteger alicePublic = g.modPow(aliceSecret, p);

        // Bob computes his public key
        BigInteger bobPublic = g.modPow(bobSecret, p);

        // Alice and Bob exchange their public keys and compute their own private keys
        BigInteger alicePrivate = bobPublic.modPow(aliceSecret, p);
        BigInteger bobPrivate = alicePublic.modPow(bobSecret, p);

        System.out.println("Alice's Private Key: " + alicePrivate);
        System.out.println("Bob's Private Key: " + bobPrivate);
    }
}


/*
---------------------------------------------------------------------------
OUTPUT
---------------------------------------------------------------------------
Alice's Private Key: 1011323962726390962226829501383981736454147347824441820307222502646113578833089375551813238562433335379279765952750265836726665787363338122201749192443069
Bob's Private Key: 1011323962726390962226829501383981736454147347824441820307222502646113578833089375551813238562433335379279765952750265836726665787363338122201749192443069

*/

// This Java program demonstrates the Diffie-Hellman Key Exchange, which is a method that allows two parties (like Alice and Bob) to securely share a secret key over a public channel — without ever directly sending the secret itself.

// 🔐 What is the Diffie-Hellman Key Exchange?
// It's a cryptographic protocol that lets two people generate a shared secret key using their own private information and values exchanged publicly.

// The final shared secret can then be used for encryption (e.g., symmetric AES encryption).

// ✅ Step-by-Step Breakdown
// 1. Prime Number (p) and Generator (g)

// BigInteger p = BigInteger.probablePrime(512, random); // Large prime
// BigInteger g = new BigInteger("3"); // Generator (public base)

// p is a large 512-bit prime number.

// g is a small constant (here, 3), used as a generator.

// These values are public and known to both parties.

// 2. Private Secrets
// BigInteger aliceSecret = new BigInteger("123456789");
// BigInteger bobSecret = new BigInteger("987654321");

// These are private numbers chosen independently by Alice and Bob.

// These are not shared publicly.

// 3. Compute Public Keys
// BigInteger alicePublic = g.modPow(aliceSecret, p);
// BigInteger bobPublic = g.modPow(bobSecret, p);
// Each party computes their public key using:
// // Public Key=g^secret mod p
// These public keys are exchanged over the network.

// // 4. Compute Shared Secret Key
// BigInteger alicePrivate = bobPublic.modPow(aliceSecret, p);
// BigInteger bobPrivate = alicePublic.modPow(bobSecret, p);

// Both parties now compute the shared secret key using:

// Shared Key=Other’s Public Key^OwnSecret mod 𝑝
// Mathematically, both arrive at the same value because:
// (g^b)^a mod p=(g^a)^b mod p=g^ab mod p

//  Result
// plaintext
// Copy
// Edit
// Alice's Private Key: 10113...
// Bob's Private Key:  10113...
// The output shows that both Alice and Bob computed the same shared secret, without ever sending their secrets.

// 💡 Why is this important?
// Even if someone intercepts g, p, alicePublic, and bobPublic, they can't compute the shared key without solving the discrete logarithm problem, which is extremely hard.
