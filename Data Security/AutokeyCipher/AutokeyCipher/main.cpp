#include<iostream>
#include<string.h>

using namespace std;

int main() {
  char msg[] = "ANAAREMERE";
  char key[] = "MIHAI";
  int msgLen = strlen(msg), keyLen = strlen(key), i, j, k;

  char* newKey = new char[msgLen];
  char*encryptedMsg = new char[msgLen];
  char* decryptedMsg = new char[msgLen];

  //generating new key
  for (i = 0, j = 0, k = 0; i < msgLen; ++i, ++j) {
    if (j >= keyLen)
    {
      newKey[i] = msg[k];
      ++k;
    }
    else
      newKey[i] = key[j];
  }

  newKey[i] = '\0';

  //encryption
  for (i = 0; i < msgLen; ++i)
    encryptedMsg[i] = ((newKey[i] - msg[i] + 26) % 26) + 'A';

  encryptedMsg[i] = '\0';

  //decryption
  for (i = 0; i < msgLen; ++i)
    decryptedMsg[i] = (((newKey[i] - encryptedMsg[i]) + 26) % 26) + 'A';

  decryptedMsg[i] = '\0';

  cout << "Original Message: " << msg;
  cout << "\nKey: " << key;
  cout << "\nNew Generated Key: " << newKey;
  cout << "\nEncrypted Message: " << encryptedMsg;
  cout << "\nDecrypted Message: " << decryptedMsg;

  return 0;
}