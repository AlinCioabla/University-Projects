#include "stdafx.h"
#include "LcG.h"

LcG::LcG(int aSeed, int aA, int aM, int aC)
  : mSeed(aSeed)
  , mA(aA)
  , mM(aM)
  , mC(aC)
{
  mCache.push_front(aSeed);
}

float LcG::Generate()
{
  auto result = (mA * mSeed + mC) % mM;

  auto found = find(mCache.begin(), mCache.end(), result) != mCache.end();

  if (found)
  {
    result = mSeed + mSeed % mM;
  }

  mCache.push_front(result);

  if (mCache.size() == 100)
    mCache.pop_back();

  mSeed = result;

  return SubUnitar(result);
}

LcG::~LcG()
{
}
