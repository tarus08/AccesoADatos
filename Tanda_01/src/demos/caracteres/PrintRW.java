package demos.caracteres;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintRW
{
    public static void main(String[] args) throws FileNotFoundException {

        String path = System.getProperty("user.dir");
        File file = new File(path + "txtFile03.txt");

        // PrintWriter to write to a file
        try (PrintWriter writer = new PrintWriter(file))
        {
            for (int i = 0; i <= 10; i++)
            {
                writer.println("Line number " + i);
            }
        }

        // you can read the file using FileReader...

        // PrintWriter to write to the console
        try (PrintWriter writer = new PrintWriter(System.out))
        {
            System.out.println("Using the PrintWriter to write to the console.");
            for (int i = 0; i <= 10; i++)
            {
                writer.println("Line number " + i);
            }
        }
    }
}
