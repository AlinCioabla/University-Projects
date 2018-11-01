#include "CaesarCipher.h"
#include <algorithm>


CaesarCipher::CaesarCipher(int offset) : offset(offset)
{
  if(offset < 0)
  {
    offset = alphabet.size() + offset;
  }
  auto originalAlphabet = alphabet;

  std::rotate(alphabet.begin(), alphabet.begin() + offset, alphabet.end());

  for(auto i = 0; i < alphabet.size(); i++)
  {
    mappedAlphabet[originalAlphabet[i]] = alphabet[i];
  }
}

std::string CaesarCipher::encrypt(const std::string& source)
{
  std::string encryptedValue = source;
  for(auto& character : encryptedValue)
  {
    character = mappedAlphabet[character];
  }
  return encryptedValue;
}

std::string CaesarCipher::decrypt(const std::string& source)
{
  std::string decryptedValue = source;
  for(auto& character : decryptedValue)
  {
    const auto correspondent = std::find_if(mappedAlphabet.begin(), mappedAlphabet.end(), [character](const auto& pair)
    {
      return pair.second == character;
    })->first;

    character = correspondent;
  }

  return decryptedValue;
}
