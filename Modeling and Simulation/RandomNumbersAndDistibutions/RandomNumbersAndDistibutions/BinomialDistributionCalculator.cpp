#include "stdafx.h"
#include "BinomialDistributionCalculator.h"

BinomialDistributionCalculator::BinomialDistributionCalculator(unsigned int aN,
                                                               float        aP,
                                                               Generator *  aGen)
  : mN(aN)
  , mP(aP)
  , mGen(aGen)
{
}

float BinomialDistributionCalculator::Generate()
{
  auto x = 0;
  for (int i = 0; i < mN; i++)
  {
    auto u = mGen->Generate();
    if (u < mP)
    {
      x++;
    }
  }

  return x / (float)(mN - 1);
}

BinomialDistributionCalculator::~BinomialDistributionCalculator()
{
}
