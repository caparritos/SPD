public class Prime extends Thread {
    int n;

    public Prime(int n) {
        this.n = n;
    }

    static int[] prime(int n) {
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


    public void run(int n) {
        prime(n);
    }
}
