#include "stdafx.h"
#include "Generator.h"

unsigned int Generator::NumberOfDigits(const unsigned long long int aNumber)
{
  if (!aNumber)
    return 1;

  auto temp = aNumber;

  auto numberOfDigits = 0;
  while (temp)
  {
    temp /= 10;
    numberOfDigits++;
  }

  return numberOfDigits;
}

float Generator::SubUnitar(const long long int aNumber)
{
  return aNumber / pow(10, NumberOfDigits(aNumber));
}