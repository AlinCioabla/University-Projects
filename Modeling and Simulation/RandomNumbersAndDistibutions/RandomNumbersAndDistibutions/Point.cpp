#include "stdafx.h"
#include "Point.h"

Point::Point(float aX, float aY)
  : x(aX)
  , y(aY)
{
}

bool Point::IsInsideCircle(float aR)
{
  return x * x + y * y <= aR * aR;
}
