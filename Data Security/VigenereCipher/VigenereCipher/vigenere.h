#ifndef VIGENERELIB_H_INCLUDED
#define VIGENERELIB_H_INCLUDED

/*The multiplied key*/
extern char key[50];
/*The text after encryption was performed*/
extern char enc[50];
/*The text after decryption was performed*/
extern char dec[50];
/*Function to perform encryption*/
char* vigenereEncrypt(char*  text, char*  key);
/*Function to perform decryption*/
char* vigenereDecrypt(char*  text, char*  key);
/*This function multiplies the key in order to have the same length as the message*/
char* vigenereKey(char*  text, char*  oneWordKey);

#endif // VIGENERELIB_H_INCLUDED