#pragma once
class GraphGenerator
{
public:
  virtual map<string, float> GetValues(unsigned int aGenNumbers) = 0;
};
