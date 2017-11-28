#pragma once
#include "Generator.h"
class Evaluator
{
public:
  Evaluator(Generator * aGen, unsigned int aNPoints);

  float Eval();

  ~Evaluator();

private:
  Generator *  mGen;
  unsigned int mNPoints;
  unsigned int mCounter;
  double       pi = 3.14159265358979323846;
};
