package actividad1_08;

import javax.swing.*;
import java.io.*;

public class WriteReadRegisters {
    public static void main(String[] args) throws IOException {

        String path = System.getProperty("user.dir");
        String filename;

        do {
            filename = JOptionPane.showInputDialog(null, "Enter the name of the file.");
            if (!filename.isEmpty()) {
                File file = new File(path, filename);
                createOrNotFile(file);
                // Write new content
                askInputAndWrite(file);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter the name of the file.");
            }
        } while (filename.trim().isEmpty());
    }


    // method to create the file, if necessary
    private static void createOrNotFile(File file) throws IOException {
        if (file.exists()) {
            JOptionPane.showMessageDialog(null, "The file already exists, both the old and new content will be shown.");
        } else {
            file.createNewFile();
            JOptionPane.showMessageDialog(null, file.getName() + " is a good name for the file.");
        }
    }

    // ask for the inputs and write them to the file
    private static void askInputAndWrite(File file) {
        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(file, true))) {

            // brand
            String brand;
            // model
            String model;
            //  year has String and int because you cannot use 'int' with joptionpane, just string
            String yearString;
            int yearInt;
            // color
            String color;
            // mate, has two for the same reason as the year...
            String mateString;
            boolean mateBoolean = false;

            // number of registers we write to the file
            int registerNumber = 0;

            for (int i = 0; i < 2; i++)
            {
                registerNumber++;
                brand = JOptionPane.showInputDialog(null, "Enter the brand of the car number " +registerNumber+".");
                writer.writeUTF(brand);
                model = JOptionPane.showInputDialog(null, "Enter the model of the " +brand);
                writer.writeUTF(model);
                yearString = JOptionPane.showInputDialog(null, "Enter the year of the " +brand);
                yearInt = Integer.parseInt(yearString);
                writer.writeInt(yearInt);
                color = JOptionPane.showInputDialog(null, "Enter the color of the " +brand);
                writer.writeUTF(color);
                mateString = JOptionPane.showInputDialog(null, "Enter 't' if the color of the " +brand+ " is mate or 'f' if it is not.");

                if (mateString.equals("t"))
                    mateBoolean = true;
                else if(mateString.equals("f"))
                    mateBoolean = false;
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid input. Try again.");
                    break;
                }
                writer.writeBoolean(mateBoolean);
            }

            // show the new content of the file
            displayContent(file);

        } catch (IOException e) {
            throw new RuntimeException("Error with FileWriter.", e);
        }
    }

    // method to display the content of the file
    private static void displayContent(File file) throws IOException {

        try (DataInputStream reader = new DataInputStream(new FileInputStream(file))) {

            while (true)
            {
                try
                {
                    String brand = reader.readUTF();
                    String model = reader.readUTF();
                    int year = reader.readInt();
                    String color = reader.readUTF();
                    boolean mate = reader.readBoolean();
                    JOptionPane.showMessageDialog(null, brand+ ", " +model+ ", " + color);
                }
                catch (EOFException e)
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

