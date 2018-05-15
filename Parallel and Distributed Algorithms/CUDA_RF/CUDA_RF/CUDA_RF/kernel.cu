#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include<iostream>
#include<algorithm>
using namespace std;

#define N 25
#define D(row,column) D[row*5 + column]

int D[N] = {
  0,3,9,8,3,
  5,0,1,4,2,
  6,6,0,4,5,
  2,9,2,0,7,
  7,9,3,2,0,
};

__global__ void CalcDist(int* D, int k)
{
  int i = threadIdx.x;
  int j = threadIdx.y;

  D(i, j) = min(D(i, j), D(i, k)) + D(k, j);
}


int main()
{
  int numBlocks = 1;
  int k;
  dim3 threadsPerBlock(N, N);

  int* d_D;
  cudaMalloc((void**)&d_D, N * sizeof(int));
  cudaMemCpy(&d_D, &D, N * sizeof(int), cudaMemcpyHostToDevice);

  for (k = 1; k <= N; ++k)
  {
    MatAdd<<<numBlocks, threadsPerBlock >>>(d_D, k);
  }
  cudaMemCpy(&D, &d_D, N * sizeof(int), cudaMemcpyDeviceToHost);
}