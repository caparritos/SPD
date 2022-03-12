//first version without threads

import java.util.Scanner;
import java.io.*;

public class Main {
    public static void createFile(String inputFileName) throws IOException {
        File myObj = new File(inputFileName);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        }
        else {
            System.out.println("File already exists.");
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String file = "";
        int n;
        if(args.length > 0)
        {
            n = Integer.parseInt(args[0]);
            file = args[0]+".txt";
        }
        else
        {
            String[] input = scanner.nextLine().split(" ");
            n = Integer.parseInt(input[0]);
            file = input[1];
        }
        createFile(file);
        int[] a = prime(n);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < n; i++)
            printWriter.println(a[i]);

        printWriter.close();
    }
}

