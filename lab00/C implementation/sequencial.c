#include <stdio.h>
#include <stdbool.h>
#include <math.h>

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

int prime(int *a, int n)
{
    int count;
    int number = 2;
    for (count = 0; count <= n; count++)
    {
        if (isPrime(number))
        {
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
        for (int i = 1; i < n; i++) // i = 1
            fprintf(f, "%s%d", separator, a[i]);
    }
}

int main()
{
    int n;
    char filename[100];
    FILE *fp;
    scanf("%d %s", &n, filename);
    int a[n];
    prime(a, n);
    fp = fopen(filename, "w");
    ints_fprint(fp, a, n, "\n");
    // closing the file pointer
    fclose(fp);

    return 0;
}
