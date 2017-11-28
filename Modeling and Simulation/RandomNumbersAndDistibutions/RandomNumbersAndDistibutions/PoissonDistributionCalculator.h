#pragma once
#include "Generator.h"
class PoissonDistributionCalculator : public Generator
{
public:
  PoissonDistributionCalculator(int aLambda, int aLimit, Generator * aGen);
  float Generate() override;

private:
  int          mLambda;
  int          mLimit;
  Generator *  mGen;
  const double e = std::exp(1.0);
};
