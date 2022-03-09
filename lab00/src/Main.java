//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
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
        Prime t1 = new Prime(n, file);
        CreateFile t2 = new CreateFile(file);


        Thread primes = new Thread(t1);
        Thread createFile = new Thread(t2);


        long start = System.currentTimeMillis();
        createFile.start();
        primes.start();

        try {
            createFile.join();
            primes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println(total);
        scanner.close();
    }
}