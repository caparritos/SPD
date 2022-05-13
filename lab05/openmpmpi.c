#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include <math.h>
#include <string.h>
#include <stdbool.h>
#include <omp.h>

int process_count;
int my_rank;

bool isPrime(int n)
{
    bool prime=true;
    if(n == 2 || n == 3 || n == 5) 
        return true;
    if(n <= 1 || (n&1) == 0)
        return false;
        
    bool flag=false;    
    # pragma omp parallel for shared(flag)
    for(int i = 3; i <= (int)sqrt(n); i += 2)
    {
        if(flag)
            continue;
	    if(n % i == 0) 
            prime=false;
        if(!prime)
            flag=true;
    }   
    return prime; 
}

int primeNumber(int *a, int min, int max)
{
    int count=0;
    for (int i = min; i <= max; i++)
    {
        if(isPrime(i))
        {
            a[count++]=i;
        }
    }
    return count;
}

int main(int argc, char *argv[])
{
    omp_set_num_threads(4);
    int n = atoi(argv[1]);
    FILE *fp = fopen(argv[2], "w");

    double start = MPI_Wtime();
    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &process_count);
    MPI_Comm_rank(MPI_COMM_WORLD,&my_rank);

    int limit;
    if (n < 6) 
        limit=12;
    else
        limit=ceil(n * (log(n) + log(log(n))));
        
    double delta = (double)limit/process_count;
    int min = (my_rank == 0)? 2 : (int)(my_rank*delta) + 1;
    int max = (my_rank == process_count-1)? limit : (int)((my_rank+1)*delta);

    int *results;
    if (my_rank == 0) {
        results = (int *) malloc(delta * sizeof (int));
    } else {
        results = (int *) malloc(delta * sizeof (int));
    }

    int n_primes=primeNumber(results, min, max);

    MPI_Barrier(MPI_COMM_WORLD);

    int number_all_primes;
    MPI_Reduce(&n_primes, &number_all_primes, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    int *final_results, *recvcounts;
    if (my_rank == 0) {
        recvcounts = malloc(process_count * sizeof(int));
        final_results = malloc (number_all_primes * sizeof(int));
    }

    MPI_Gather(&n_primes, 1, MPI_INT, recvcounts, 1, MPI_INT, 0, MPI_COMM_WORLD);

    int *displs;
    if (my_rank == 0) {
        displs = malloc(process_count * sizeof(int));
        displs[0] = 0;
        for (int i=1; i<process_count; i++) {
           displs[i] = displs[i-1] + recvcounts[i-1];
        }
    }

    MPI_Gatherv(results, n_primes, MPI_INT, final_results, recvcounts, displs, MPI_INT, 0, MPI_COMM_WORLD);

    if (my_rank == 0) {
        for (int i = 0; i < n; i++) {
            fprintf(fp, "%d\n" , final_results[i]);
        }
    }

    MPI_Finalize();
    double elapsed = MPI_Wtime() - start;
    printf("\nElapsed time:  %1.3f seconds.\n", elapsed);

    return 0;
}
