#pragma once
class Generator
{
public:
  virtual float Generate() = 0;

protected:
  unsigned int NumberOfDigits(const unsigned long long int aNumber);
  float        SubUnitar(const long long int aNumber);
};
