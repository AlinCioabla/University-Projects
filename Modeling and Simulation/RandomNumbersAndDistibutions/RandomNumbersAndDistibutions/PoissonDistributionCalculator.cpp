#include "stdafx.h"
#include "PoissonDistributionCalculator.h"

PoissonDistributionCalculator::PoissonDistributionCalculator(int         aLambda,
                                                             int         aLimit,
                                                             Generator * aGen)
  : mLambda(aLambda)
  , mLimit(aLimit)
  , mGen(aGen)
{
}

float PoissonDistributionCalculator::Generate()
{
  float L = 1 / pow(e, mLambda);
  int   k = 0;
  float p = 1;

  do
  {
    k++;
    float u = mGen->Generate();
    p       = p * u;
  } while (p > L && k <= mLimit);

  return (k - 1) / (float)(mLimit);
}
