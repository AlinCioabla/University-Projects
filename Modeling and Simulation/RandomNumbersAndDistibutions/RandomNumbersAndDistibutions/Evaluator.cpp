#include "stdafx.h"
#include "Evaluator.h"
#include "Point.h"

Evaluator::Evaluator(Generator * aGen, unsigned int aNPoints)
  : mGen(aGen)
  , mNPoints(aNPoints)
  , mCounter(0)
{
}

float Evaluator::Eval()
{
  for (int i = 1; i <= mNPoints; i++)
  {
    Point p(mGen->Generate(), mGen->Generate());
    if (p.IsInsideCircle())
    {
      mCounter++;
    }
  }
  return fabs(pi - 4 * (float)mCounter / mNPoints);
}

Evaluator::~Evaluator()
{
}
