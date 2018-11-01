#pragma once
#include<map>
#include<string>

class CaesarCipher
{
public:
  CaesarCipher(int offset);

  std::string encrypt(const std::string& source);

  std::string decrypt(const std::string& source);

private:
  std::string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  std::map<char, char> mappedAlphabet;
  int offset;
};

