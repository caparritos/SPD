//first version without threads

import java.util.Scanner;
import java.io.*;

public class Lab0 {
    public static void createFile(String inputFileName) {
        try {
            File myObj = new File(inputFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int[] prime(int n) {
        int number = 2;
        int count = 0;
        int[] a = new int[n];

        while (count != n) {
            boolean prime = true;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                a[count] = number;
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

    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        String[] arrOfStr = input.split(" ");
        int n = Integer.parseInt(arrOfStr[0]);
        createFile(arrOfStr[1]);
        int[] a = prime(n);
        FileWriter fileWriter = new FileWriter(arrOfStr[1]);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < n; i++)
            printWriter.println(a[i]);

        printWriter.close();
    }
}
