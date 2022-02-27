import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class writeinfile extends Thread {
    int[] prime;
    String file;

    public writeinfile(int[] prime, String file) {
        this.prime = prime;
        this.file = file;
    }

    public void writeinfile(String input) {

        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < prime.length; i++) {
                printWriter.println(prime[i]);
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void run() {
        writeinfile(file);
    }
}
