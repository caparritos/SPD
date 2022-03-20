#include<stdio.h> 
#include<stdbool.h> 
#include<math.h>


int prime(int *a,int n) {
    int number = 2;
    int count=0;
    while(count != n)
    {
        bool prime = true;
        for(int i = 2; i <= sqrt(number); i++)
        {
            if(number % i == 0)
            {
                prime = false;
                break;
            }
        }
        if(prime)
        {
            a[count]=number;
            count++;
        }
        number++;
    }
       return count; 
}

void ints_fprint(FILE *f, const int *a, int n, const char *separator)
{
  if (n > 0)
  {
    fprintf(f, "%d", a[0]);
    for (int i = 1; i < n; i++)  // i = 1
      fprintf(f, "%s%d", separator, a[i]);
  }
}


int main() 
{ 
    int n;
    char filename [100]; 
    FILE *fp; 
    scanf("%d %s", &n, filename);
    int a[n];
    prime(a,n);
    fp = fopen(filename, "w"); 
    ints_fprint(fp,a,n,"\n");
    // closing the file pointer 
    fclose(fp); 

    return 0; 
}