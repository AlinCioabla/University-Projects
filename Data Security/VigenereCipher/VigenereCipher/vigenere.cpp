#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "vigenere.h"

/*External variables*/
/*The multiplied key*/
char key[50];
/*The text after encryption was performed*/
char enc[50];
/*The text after decryption was performed*/
char dec[50];

/*Public functions*/
/*Function to perform encryption*/
char* vigenereEncrypt(char* text, char* key)
{
  for (int i = 0; i < strlen(text); i++)
  {
    char x = (text[i] + key[i]) % 26 + 'A';

    enc[i] = x;
  }
  return enc;
}
/*Function to perform decryption*/
char* vigenereDecrypt(char* text, char* key)
{
  for (int i = 0; i < strlen(text); i++)
  {
    char x = (text[i] - key[i] + 26) % 26 + 'A';

    dec[i] = x;
  }
  return dec;
}

/*This function multiplies the key in order to have the same length as the message*/
char* vigenereKey(char* text, char* oneWordKey)
{
  int x = strlen(oneWordKey);
  int  i = 0, j = 0;

  while (strlen(text) > strlen(key))
  {
    if (strlen(oneWordKey) == i)
    {
      i = 0;
    }
    key[j] = oneWordKey[i];
    i++;
    j++;
  }
  key[j] = '\n';
  return key;
}