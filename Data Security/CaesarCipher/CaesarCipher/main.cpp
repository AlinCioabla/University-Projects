#include <iostream>
#include "CaesarCipher.h"

int main()
{
  CaesarCipher caesarCipher(3);
  auto sentence = "ANA ARE MERE";
  auto encryptedValue = caesarCipher.encrypt(sentence);
  auto decryptedValue = caesarCipher.decrypt(encryptedValue);

  std::cout << "Original: " << sentence << std::endl;
  std::cout << "Encrypted: " << encryptedValue << std::endl;
  std::cout << "Decrypted: " << decryptedValue << std::endl;

  int x;
  std::cin >> x;
}