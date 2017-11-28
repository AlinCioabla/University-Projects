#pragma once
#include "Generator.h"
class LcG : public Generator
{
public:
  LcG(int aSeed, int aA, int aM, int aC);

  float Generate();

  ~LcG();

private:
  int        mSeed;
  int        mA;
  int        mM;
  int        mC;
  deque<int> mCache;
};
