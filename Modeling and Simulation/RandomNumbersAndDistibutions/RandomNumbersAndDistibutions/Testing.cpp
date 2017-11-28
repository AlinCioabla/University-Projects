// Testing.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinomialDistributionCalculator.h"
#include "DistributionGraphGenerator.h"
#include "Evaluator.h"
#include "LcG.h"
#include "MdSq.h"
#include "PoissonDistributionCalculator.h"

void TestMdSq()
{
  MdSq mdsq(123);
  for (auto i = 1; i <= 30; i++)
    cout << mdsq.Generate() << endl;
}

void TestLcG()
{
  LcG lcg(123, 5, 7, 9);
  for (auto i = 1; i <= 30; i++)
    cout << lcg.Generate() << endl;
}

void TestEval()
{
  MdSq      mdsq(123);
  LcG       lcg(123, 5, 7, 9);
  Evaluator evaluator1(&mdsq, 7500);
  cout << evaluator1.Eval();

  cout << endl;

  Evaluator evaluator2(&lcg, 7500);

  cout << evaluator2.Eval();
}

int main()
{
  // TestEval();

  MdSq mdsq(123);
  LcG  lcg(123, 5, 7, 9);

  BinomialDistributionCalculator bin(30, 0.4f, &mdsq);
  PoissonDistributionCalculator  poisson(2, 15, &mdsq);
  DistributionGraphGenerator     gg(&poisson);

  auto res = gg.GetValues(7500);
  for (auto & item : res)
  {
    cout << item.first << ": " << item.second << endl;
  }

  return 0;
}
