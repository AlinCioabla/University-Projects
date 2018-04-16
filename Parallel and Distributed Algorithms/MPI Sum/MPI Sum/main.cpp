#include "mpi.h"
#include <stdio.h>
#include <iostream>
#include <vector>
using namespace std;

#define N 1000
#define MASTER	0


int main(int argc, char* argv[]) {
  int processId, numberOfProcesses;

  MPI_Status status;

  int totalSum, partialSum;

  int i, numberOfElements, id, numberOfElemChild, step, start, end;

  int elements[N];

  MPI_Init(&argc, &argv);

  MPI_Comm_rank(MPI_COMM_WORLD, &processId);
  MPI_Comm_size(MPI_COMM_WORLD, &numberOfProcesses);

  numberOfElements = 50;

  if (processId == MASTER)
  {
    step = numberOfElements / numberOfProcesses;

    for (i = 0; i < numberOfElements; i++)
      elements[i] = i;

    for (id = 1; id < numberOfProcesses; id++) {
      start = (id - 1)* step;
      end = id * step - 1;

      if (id == numberOfProcesses - 1)
      {
        end = numberOfElements - 1;
      }

      numberOfElemChild = end - start + 1;

      MPI_Send(&numberOfElemChild, 1, MPI_INT, id, 1, MPI_COMM_WORLD);
      MPI_Send(&elements[start], numberOfElemChild, MPI_INT, id, 1, MPI_COMM_WORLD);
    }

    totalSum = 0;

    for (id = 1; id < numberOfProcesses; id++) {
      MPI_Recv(&partialSum, 1, MPI_INT, id, 1, MPI_COMM_WORLD, &status);
      totalSum += partialSum;
    }

    cout << "Total: " << totalSum << '\n';
  }
  else
  {
    int local_elements[N];
    MPI_Recv(&numberOfElemChild, 1, MPI_INT, MASTER, 1, MPI_COMM_WORLD, &status);
    MPI_Recv(&local_elements, numberOfElemChild, MPI_INT, MASTER, 1, MPI_COMM_WORLD, &status);

    partialSum = 0;

    for (i = 0; i < numberOfElemChild; i++)
      partialSum += local_elements[i];

    cout << "Partial sum that was calculated by process " << processId << " : " << partialSum << "\n";
    MPI_Send(&partialSum, 1, MPI_INT, MASTER, 1, MPI_COMM_WORLD);
  }
  MPI_Finalize();
}