from Crypto.Cipher import AES
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Random import get_random_bytes
import base64


def caesar_encrypt(plain_text, shift):
    encrypted = ""
    for char in plain_text:
        if char.isalpha():
            shift_base = ord('A') if char.isupper() else ord('a')
            encrypted += chr((ord(char) - shift_base + shift) % 26 + shift_base)
        else:
            encrypted += char
    return encrypted

def caesar_decrypt(encrypted_text, shift):
    return caesar_encrypt(encrypted_text, -shift)


def aes_encrypt(plain_text, key):
    cipher = AES.new(key, AES.MODE_EAX)
    ciphertext, tag = cipher.encrypt_and_digest(plain_text.encode('utf-8'))
    return base64.b64encode(cipher.nonce + tag + ciphertext).decode('utf-8')

def aes_decrypt(enc_text, key):
    enc_bytes = base64.b64decode(enc_text)
    nonce, tag, ciphertext = enc_bytes[:16], enc_bytes[16:32], enc_bytes[32:]
    cipher = AES.new(key, AES.MODE_EAX, nonce=nonce)
    return cipher.decrypt_and_verify(ciphertext, tag).decode('utf-8')


def rsa_generate_keys():
    key = RSA.generate(2048)
    private_key = key.export_key()
    public_key = key.publickey().export_key()
    return private_key, public_key

def rsa_encrypt(plain_text, public_key):
    rsa_public_key = RSA.import_key(public_key)
    cipher = PKCS1_OAEP.new(rsa_public_key)
    return base64.b64encode(cipher.encrypt(plain_text.encode('utf-8'))).decode('utf-8')

def rsa_decrypt(enc_text, private_key):
    rsa_private_key = RSA.import_key(private_key)
    cipher = PKCS1_OAEP.new(rsa_private_key)
    return cipher.decrypt(base64.b64decode(enc_text)).decode('utf-8')


if __name__ == "__main__":
    
    caesar_shift = 3
    original_caesar = "Hello World!"
    encrypted_caesar = caesar_encrypt(original_caesar, caesar_shift)
    decrypted_caesar = caesar_decrypt(encrypted_caesar, caesar_shift)
    print(f"Caesar Cipher: {original_caesar} -> {encrypted_caesar} -> {decrypted_caesar}")

    
    aes_key = get_random_bytes(16)
    original_aes = "Hello AES!"
    encrypted_aes = aes_encrypt(original_aes, aes_key)
    decrypted_aes = aes_decrypt(encrypted_aes, aes_key)
    print(f"AES: {original_aes} -> {encrypted_aes} -> {decrypted_aes}")

   
    private_key, public_key = rsa_generate_keys()
    original_rsa = "Hello RSA!"
    encrypted_rsa = rsa_encrypt(original_rsa, public_key)
    decrypted_rsa = rsa_decrypt(encrypted_rsa, private_key)
    print(f"RSA: {original_rsa} -> {encrypted_rsa} -> {decrypted_rsa}")
