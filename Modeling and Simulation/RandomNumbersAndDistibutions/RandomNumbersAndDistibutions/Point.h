#pragma once
class Point
{
public:
  Point(float aX, float aY);

  bool IsInsideCircle(float aR = 1);

  float x;
  float y;
};
