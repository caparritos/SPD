import java.io.File;
import java.io.IOException;

public class CreateFile extends Thread {
    String file;

    public CreateFile(String file) {
        this.file = file;
    }

    public static void createFile(String inputFileName) {

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

   
    public void run() {
        createFile(file);
    }
}
