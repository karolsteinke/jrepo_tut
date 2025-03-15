import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FilesExample {
    public static void main(String[] args) throws Exception {
        
        try {
            //get a name for a file
            Scanner scanner = new Scanner(System.in);
            System.out.print("Give a name for a file:");
            String path = scanner.nextLine() + ".txt";
            scanner.close();
            
            //create a file
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }

            //write to a file
            FileWriter writer = new FileWriter(path);
            writer.write("*** SAMPLE INPUT PLACED BY CODE: HELLO! ***");
            writer.close();
        }
        catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
