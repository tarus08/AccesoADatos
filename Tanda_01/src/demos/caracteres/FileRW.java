package demos.caracteres;

import java.io.*;

public class FileRW
{
    public static void main(String[] args)
    {
        String path = System.getProperty("user.dir");
        File file = new File(path+ "txtFile.txt");
        // write to the file using FileWriter
        try (FileWriter writer = new FileWriter(file))
        {
            String string01 = "This line contains accent marks and jans";
            char[] charArray = string01.toCharArray();
            try
            {
                for (char c : charArray) {
                    writer.write(c);
                }
                writer.write("*");
                writer.write("\n");
                writer.write(charArray);

                String string02 = "\nAnother phrase";
                writer.write(string02);

                String [] provinces = {"Albacete", "Ávila", "Badajoz"};
                for (String province : provinces)
                {
                    writer.write(province);
                    writer.write("\n");
                }

                writer.append("\nMessage").append(" appended ").append(" with append.\n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // reading the file using FileReader
        try (FileReader reader = new FileReader(file))
        {
            int i;
            while ((i = reader.read()) != -1)
            {
                System.out.print((char)i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
