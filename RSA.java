import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * RSA Encryption and Decryption Implementation.
 * This program demonstrates the use of RSA for secure data transmission.
 * The RSA algorithm generates a public/private key pair, which is used to encrypt and decrypt messages.
 *
 * Author: Rekklessss
 * Divyanshu Pabia
 */
public class RSA {

    private BigInteger modulus; // The modulus (n) used for both encryption and decryption
    private BigInteger privateExponent; // The private key exponent (d)
    private BigInteger publicExponent; // The public key exponent (e)

    // Constructor to initialize RSA with specified key length (in bits)
    public RSA(int bitLength) {
        generateKeyPair(bitLength);
    }

    /**
     * Encrypts a plain text message and returns the encrypted message as a string.
     *
     * @param plainText The message to be encrypted.
     * @return The encrypted message in string form.
     */
    public synchronized String encrypt(String plainText) {
        return (new BigInteger(plainText.getBytes())).modPow(publicExponent, modulus).toString();
    }

    /**
     * Encrypts a plain text message and returns the encrypted message as a BigInteger.
     *
     * @param plainMessage The message to be encrypted in BigInteger form.
     * @return The encrypted message as a BigInteger.
     */
    public synchronized BigInteger encrypt(BigInteger plainMessage) {
        return plainMessage.modPow(publicExponent, modulus);
    }

    /**
     * Decrypts an encrypted message and returns the plain text.
     *
     * @param encryptedText The encrypted message in string form.
     * @return The decrypted message in plain text form.
     */
    public synchronized String decrypt(String encryptedText) {
        return new String((new BigInteger(encryptedText)).modPow(privateExponent, modulus).toByteArray());
    }

    /**
     * Decrypts an encrypted message and returns the plain text as a BigInteger.
     *
     * @param encryptedMessage The encrypted message in BigInteger form.
     * @return The decrypted message as a BigInteger.
     */
    public synchronized BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateExponent, modulus);
    }

    /**
     * Generates a new public and private key pair.
     *
     * @param bitLength The bit length for the modulus.
     */
    public final synchronized void generateKeyPair(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger prime1 = new BigInteger(bitLength / 2, 100, random);
        BigInteger prime2 = new BigInteger(bitLength / 2, 100, random);
        modulus = prime1.multiply(prime2); // n = p * q

        BigInteger phi = (prime1.subtract(BigInteger.ONE)).multiply(prime2.subtract(BigInteger.ONE)); // φ(n) = (p-1)(q-1)

        publicExponent = BigInteger.valueOf(3L); // Start with a small odd value for the public exponent

        // Ensure that public exponent is coprime with φ(n)
        while (phi.gcd(publicExponent).intValue() > 1) {
            publicExponent = publicExponent.add(BigInteger.TWO);
        }

        privateExponent = publicExponent.modInverse(phi); // Calculate the private exponent
    }

    /**
     * Main method to test RSA encryption and decryption with user inputs.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the bit length for RSA key generation (e.g., 512, 1024): ");
        int bitLength = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Create RSA object and generate key pair
        RSA rsa = new RSA(bitLength);

        System.out.println("Enter a message to encrypt: ");
        String message = scanner.nextLine();

        // Encrypt the message
        String encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
