#include <stdio.h>
#include "vigenere.h"
int main()
{
  char str[] = "ANAAREMERE";
  char keyword[] = "NICU";

  printf("Original text : %s\n", str);

  vigenereKey(str, keyword);
  printf("Key : %s\n", key);
  vigenereEncrypt(str, key);
  printf("Encrypted text : %s\n", enc);
  vigenereDecrypt(enc, key);
  printf("Decrypted text : %s\n", dec);

  return 0;
}
