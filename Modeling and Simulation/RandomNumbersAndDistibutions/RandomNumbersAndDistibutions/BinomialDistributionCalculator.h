#pragma once
#include "Generator.h"
class BinomialDistributionCalculator : public Generator
{
public:
  BinomialDistributionCalculator(unsigned int aN, float aP, Generator * aGen);

  float Generate() override;

  ~BinomialDistributionCalculator();

private:
  unsigned int mN;
  float        mP;
  Generator *  mGen;
};
