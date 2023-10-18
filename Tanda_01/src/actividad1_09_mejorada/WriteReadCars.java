package actividad1_09_mejorada;

import javax.swing.*;
import java.io.*;
import java.util.Objects;
public class WriteReadCars
{
    public static void main(String[] args) throws IOException
    {

        String path = System.getProperty("user.dir");
        String filename;

        do
        {
            filename = JOptionPane.showInputDialog(null, "Enter the name of the file.");

                File file = new File(path, filename);
                if (file.exists())
                {
                    JOptionPane.showMessageDialog(null, "The file already exists, the new content won't overwrite the old one.");
                } else {
                    file.createNewFile();
                    JOptionPane.showMessageDialog(null, file.getName() + " is a good name for the file.");
            }
            askInputAndWrite(file);
        }
            while (Objects.requireNonNull(filename).trim().isEmpty());
    }
    private static void askInputAndWrite(File file) throws IOException {
        // brand
        String brand;
        // model
        String model;
        // year
        int year;
        // color
        String color;
        // mate
        boolean mate;

        try (FileOutputStream fos = new FileOutputStream(file, true);
            AppendObjectOutputStream writer = new AppendObjectOutputStream(fos)) {
            for (int i = 0; i < 2; i++) {
                // asking for the input
                JOptionPane.showMessageDialog(null, "Enter the data for the " + (i + 1) + " car.");
                brand = JOptionPane.showInputDialog(null, "Brand:");
                model = JOptionPane.showInputDialog(null, "Model:");
                year = Integer.parseInt(JOptionPane.showInputDialog(null, "Year:"));
                color = JOptionPane.showInputDialog(null, "Color:");
                mate = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "'t' if the color is mate; 'f' if it is not."));

                // create the car object
                Car car = new Car(brand, model, year, color, mate);

                // write the car object to the file
                writer.writeObject(car);
            }
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // read the object from the file
            while (true) {
                try {
                    Car car = (Car) ois.readObject();
                    JOptionPane.showMessageDialog(null, " " + car.getBrand() + " " + car.getModel() + " " + car.getYear() + " " + car.getColor() + " " + car.isMate());
                } catch (EOFException e) {
                    // End of file reached
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
