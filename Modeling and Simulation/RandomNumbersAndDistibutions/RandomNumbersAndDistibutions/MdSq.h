#pragma once
#include "Generator.h"
class MdSq : public Generator
{
public:
  MdSq(unsigned long long int aSeed);
  float Generate() override;

private:
  unsigned int mSeed;
  int          mNDSeed;
};
