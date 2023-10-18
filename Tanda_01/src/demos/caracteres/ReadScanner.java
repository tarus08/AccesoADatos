package demos.caracteres;

import java.io.*;
import java.util.Scanner;

public class ReadScanner
{
    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.dir");
        File file = new File(path + "txtFile04.txt");

        // array of the names
        String [] names = {"juan", "ernesto", "alicia"};

        // array of the surnames
        String [] surnames = {"pérez pérez", "sánchez sánchez", "sanz sanz"};

        // array of the mails
        String [] mails = {"juan@.com", "ernesto@.com", "alicia@.com"};

        // write them to the file using FileWriter
        try (FileWriter writer = new FileWriter(file))
        {
            for (int i = 0; i < 3; i++)
            {
                // number for the list
                writer.write(i+". ");
                // write the names
                writer.write(names[i]);
                // write the semicolon
                writer.write(" ; ");
                // write the surnames
                writer.write(surnames[i]);
                // write another semicolon
                writer.write(" ; ");
                // write the mails
                writer.write(mails[i]);
                // new line
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // read the file using Scanner, the semicolons must disappear
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine().replaceAll(";", " "));
            }
        }
    }
}
