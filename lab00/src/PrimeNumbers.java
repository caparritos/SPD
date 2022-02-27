import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;  // Import the Scanner class
import java.io.*;
import java.io.FileWriter;



public class PrimeNumbers extends Thread{
    public static void creatFile( String  inputFileName){
        try {
            File myObj = new File(inputFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File updated.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

   /* private static boolean ehPrimoBoolean(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;
        }
        return true;
    }

    private static int[] ehPrimo() {
        int count=2;
        int[] a =new int[10000];
        int max=10000;
        for (int j = 0; j < max; j++) {
            if (ehPrimoBoolean(count)){
                a[j]=count;
            }
            else{
                j--;
            }
            count++;
        }
        return a;
    }*/

    private static int[] prime(int n) {
        int number = 2;
        int count=0;
        int[] a = new int[1000000000];

        while(count != n)
        {
            boolean prime = true;

            for(int i = 2; i <= Math.sqrt(number); i++)
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
        return a;
    }

    public static double calculateAverageExecutionTime(int n) {
        int trials = 30;
        double totalTime = 0;
        for (int i = 0; i < trials; i++) {
            long time = System.currentTimeMillis();
            prime(n);
            totalTime += System.currentTimeMillis() - time;
        }
        return totalTime / trials;
    }

    public static void main(String[] args)  throws IOException {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input=myObj.nextLine();
        String[] arrOfStr = input.split(" ");
        Thread p= new Thread();
        int n=Integer.parseInt(arrOfStr[0]);
        creatFile(arrOfStr[1]);
        int[] a = prime(n);
        FileWriter fileWriter = new FileWriter(arrOfStr[1]);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i<n ;i++) {
            printWriter.println(a[i]);
        }
        printWriter.close();
         /*int n = 125;
        double previousTime = calculateAverageExecutionTime(n);
        double newTime;
        double doublingRatio;

        for(int i = 250; true; i*=2)
        {
            newTime = calculateAverageExecutionTime(i);
            if(previousTime > 0)
                doublingRatio = newTime/previousTime;
            else
                doublingRatio = 0;

            previousTime = newTime;
            System.out.println(i + "\t" + newTime + "\t" + doublingRatio);
        }*/
    }

}
