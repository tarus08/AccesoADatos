import java.io.*;

public class actividad1_12
{
    public static void main(String[] args) throws IOException {
        // create the file
        String path = System.getProperty("user.dir");
        System.out.println(path);
        File file = new File(path + "caracteres.txt");

        // write to the file using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            String phrase = "Este es un método que añade contenido a un fichero de texto carácter a carácter";
            writer.write(phrase);
        }

        // read the file using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                // convert the phrase to upper case and remove the space in between words
                System.out.println(line.toUpperCase().replaceAll(" ", ""));
            }
        }
    }
}
