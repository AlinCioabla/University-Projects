#include "stdafx.h"
#include "MdSq.h"

MdSq::MdSq(unsigned long long int aSeed)
  : mSeed(aSeed)
{
  mNDSeed = NumberOfDigits(mSeed);
}

float MdSq::Generate()
{
  int squaredSeed     = mSeed * mSeed;
  int nDigitsToRemove = NumberOfDigits(squaredSeed) - mNDSeed;

  int nDigitsRemovedFromBack;
  int nDigitsRemovedFromFront;

  if (nDigitsToRemove % 2 == 0)
  {
    nDigitsRemovedFromBack = nDigitsToRemove / 2;
  }
  else
  {
    nDigitsRemovedFromBack = nDigitsToRemove / 2 + 1;
  }
  nDigitsRemovedFromFront = nDigitsToRemove / 2;

  squaredSeed /= pow(10, nDigitsRemovedFromBack);
  squaredSeed =
    (squaredSeed % (unsigned int)pow(10, NumberOfDigits(squaredSeed) - nDigitsRemovedFromFront));

  if (NumberOfDigits(squaredSeed) < mNDSeed)
  {
    squaredSeed = squaredSeed + pow(10, mNDSeed - 1);
    squaredSeed += 5;
  }

  mSeed = squaredSeed;

  return SubUnitar(squaredSeed);
}
