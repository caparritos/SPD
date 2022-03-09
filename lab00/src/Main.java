//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input = myObj.nextLine();
        String[] arrOfStr = input.split(" ");
        int n = Integer.parseInt(arrOfStr[0]);
        String file = arrOfStr[1];
        Prime t1 = new Prime(n,file);
        CreateFile t2 = new CreateFile(file);
        //writeinfile t3 = new writeinfile(a, file);

        Thread createFile = new Thread(t2);
        Thread primes = new Thread(t1);

        //Thread writeinfile = new Thread(t3);


        long start = System.currentTimeMillis();
        createFile.start();
        primes.start();

        //writeinfile.start();

        try {
            // join() method waits for the thread to die
            createFile.join();
            primes.join();

            //writeinfile.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println(total);

    }
}
