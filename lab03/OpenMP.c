#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <omp.h>

bool isPrime(int n)
{
    bool prime = true;
    if (n == 2 || n == 3 || n == 5)
        return true;
    if (n <= 1 || (n & 1) == 0) // n&1 faz AND, ve se e par, se for mata o processo
        return false;
    for (int i = 3; i <= (int)sqrt(n); i += 2)
    {
        if (n % i == 0)
            prime = false;
        if (!prime)
            break;
    }
    return prime;
}

int primeNumber(int *a, int n)
{
    int count;
    int number = 2;
#pragma omp parallel \
shared(number, count)
#pragma omp parallel for private(number)
    for (count = 0; count <= n; count++)
    {
        if (isPrime(number))
        {
            //#pragma omp critical
            a[count] = number;
        }
        else
        {
            count--;
        }
        number++;
    }
    return n;
}

void ints_fprint(FILE *f, const int *a, int n, const char *separator)
{
    if (n > 0)
    {
        fprintf(f, "%d", a[0]);
        for (int j = 1; j < n; j++) // i = 1
            fprintf(f, "%s%d", separator, a[j]);
    }
}

int main()
{
    omp_set_num_threads(3);
    int n;
    int tid;
    char filename[100];
    FILE *fp;
    scanf("%d %s", &n, filename);
    fp = fopen(filename, "w");
    int a[n];
    // time_t start, end;
    clock_t start, end;
    // start = time(&start);
    start = clock();

    primeNumber(a, n);
    // selectionSort(a,n);
    // end = time(&end);
    end = clock();
    // printf("%ld\n", (end-start));
    printf("%f\n", (double)(end - start) / CLOCKS_PER_SEC);
    ints_fprint(fp, a, n, "\n");
    fclose(fp);
    return 0;
}
