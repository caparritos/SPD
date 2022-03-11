import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Prime extends Thread {
    int n;
    String file;

    public Prime(int n, String file) {

        this.n = n;
        this.file= file;
    }

    static void prime(int n, String file) throws IOException{
        int number = 2;
        int count = 0;

        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        while (count != n) {
            boolean prime = true;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                printWriter.println(number);
                count++;
            }
            number++;


        }
            printWriter.close();
    }



    public void run() {

        try {
            prime(n,file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
