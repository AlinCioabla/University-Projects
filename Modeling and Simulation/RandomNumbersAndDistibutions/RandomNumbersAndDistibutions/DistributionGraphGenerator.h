#pragma once
#include "Generator.h"
#include "GraphGenerator.h"
class DistributionGraphGenerator : public GraphGenerator
{
public:
  DistributionGraphGenerator(Generator * aGen);
  map<string, float> GetValues(unsigned int aGenNumbers) override;
  map<string, float> GetResult();

private:
  Generator *        mGen;
  map<string, float> mResult;
};
