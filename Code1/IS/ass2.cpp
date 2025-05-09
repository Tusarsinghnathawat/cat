#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

string encrypt(string plaintext, int key) {
    string ciphertext = "";
    int len = plaintext.length();
    while (len % key != 0) {
        plaintext += '_';
        len++;
    }
    for (int col = 0; col < key; col++) {
        for (int row = 0; row < len / key; row++) {
            ciphertext += plaintext[row * key + col];
        }
    }
    return ciphertext;
}

string decrypt(string ciphertext, int key) {
    string plaintext = "";
    int len = ciphertext.length();
    int rows = len / key;
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < key; col++) {
            plaintext += ciphertext[col * rows + row];
        }
    }
    plaintext.erase(remove(plaintext.begin(), plaintext.end(), '_'), plaintext.end());
    return plaintext;
}

int main() {
    string text = "HELLOTRANSPOSITION";
    int key = 5;
    cout << "Original Text: " << text << endl;
    string encrypted = encrypt(text, key);
    cout << "Encrypted Text: " << encrypted << endl;
    string decrypted = decrypt(encrypted, key);
    cout << "Decrypted Text: " << decrypted << endl;
    return 0;
}


//  What is a Transposition Cipher?
// Instead of changing the letters (like Caesar cipher), it rearranges the order of the letters in a systematic way.

// ✅ Encryption Logic
// cpp
// Copy
// Edit
// string encrypt(string plaintext, int key)
// Padding the plaintext:

// If the length of the text isn't divisible by the key (number of columns), it adds underscores _ to fill the grid.

// This ensures you can make a rectangle of rows and columns.

// Column-wise reading:

// It reads the characters column by column from the rectangle and stores them into the encrypted message.

// 🔄 Example:
// For "HELLOTRANSPOSITION" and key = 5, it would form this grid:

// mathematica
// Copy
// Edit
// H E L L O  
// T R A N S  
// P O S I T  
// I O N _ _  
// Then read column-wise:

// rust
// Copy
// Edit
// HTPI  EROO  LASN  LNII  OS_T
// ↓     ↓      ↓      ↓     ↓
// H -> T -> P -> I    (1st col)
// E -> R -> O -> O    (2nd col)
// L -> A -> S -> N    ...
// L -> N -> I -> I
// O -> S -> T -> _

// Final Encrypted: HTPIEROOLASNLNIIOST_
// 🔓 Decryption Logic
// cpp
// Copy
// Edit
// string decrypt(string ciphertext, int key)
// It calculates the number of rows from the length and key.

// It fills the grid column by column using the encrypted text.

// Then reads it row by row to reconstruct the original message.

// Removes the extra _ padding at the end.

// 🧪 What the main() does:
// cpp
// Copy
// Edit
// string text = "HELLOTRANSPOSITION";
// int key = 5;
// It sets up an original message and a key (5 columns).

// Encrypts the text.

// Then decrypts it back to verify that it works.

