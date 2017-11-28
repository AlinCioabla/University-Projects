#include "stdafx.h"
#include "DistributionGraphGenerator.h"

DistributionGraphGenerator::DistributionGraphGenerator(Generator * aGen)
  : mGen(aGen)
{
}

map<string, float> DistributionGraphGenerator::GetValues(unsigned int aGenNumbers)
{
  for (int i = 0; i < aGenNumbers; i++)
  {
    auto number = mGen->Generate();
    if (number >= 0 && number < 0.1f)
    {
      mResult["0-0.1"]++;
    }
    else if (number >= 0.1f && number < 0.2f)
    {
      mResult["0.1-0.2"]++;
    }
    else if (number >= 0.2f && number < 0.3f)
    {
      mResult["0.2-0.3"]++;
    }
    else if (number >= 0.3f && number < 0.4f)
    {
      mResult["0.3-0.4"]++;
    }
    else if (number >= 0.4f && number < 0.5f)
    {
      mResult["0.4-0.5"]++;
    }
    else if (number >= 0.5f && number < 0.6f)
    {
      mResult["0.5-0.6"]++;
    }
    else if (number >= 0.6f && number < 0.7f)
    {
      mResult["0.6-0.7"]++;
    }
    else if (number >= 0.7f && number < 0.8f)
    {
      mResult["0.7-0.8"]++;
    }
    else if (number >= 0.8f && number < 0.9f)
    {
      mResult["0.8-0.9"]++;
    }
    else if (number >= 0.9f && number <= 1)
    {
      mResult["0.9-1"]++;
    }
  }
  return mResult;
}

map<string, float> DistributionGraphGenerator::GetResult()
{
  return mResult;
}
