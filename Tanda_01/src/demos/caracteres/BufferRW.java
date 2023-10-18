package demos.caracteres;

import java.io.*;

public class BufferRW
{
    public static void main(String[] args)
    {

        String path = System.getProperty("user.dir");
        File file = new File(path+ "txtFile02.txt");

        // write using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (int i = 0; i <= 10; i++)
            {
                writer.write("Line number " +i);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // read using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
