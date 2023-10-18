import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class actividad1_11
{
    public static void main(String[] args) throws IOException
    {
        String path = System.getProperty("user.dir");
        File file = new File(path + "caracteres.txt");

        // write to the file using FileWriter
        try (FileWriter writer = new FileWriter(file))
        {
            String phrase = "Este es un método que añade contenido a un fichero de texto carácter a carácter";
            char[] chars = phrase.toCharArray();

            for (char aChar : chars)
            {
                // write byte to byte
                writer.write(aChar);
            }
        }

        // read the file and delete the space between words using a Scanner object
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine().replaceAll(" ", ""));
            }
        }
    }
}