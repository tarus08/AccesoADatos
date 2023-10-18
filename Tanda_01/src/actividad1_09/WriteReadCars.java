package actividad1_09;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class WriteReadCars {
    public static void main(String[] args) throws IOException {

        String path = System.getProperty("user.dir");
        String filename;

        do {
            filename = JOptionPane.showInputDialog(null, "Enter the name of the file.");
            if (!filename.isEmpty()) {
                File file = new File(path, filename);
                if (file.exists()) {
                    JOptionPane.showMessageDialog(null, "The file already exists, the new content won't overwrite the old one.");
                    askInputAndWrite(file);
                } else {
                    if (file.createNewFile()) {
                        JOptionPane.showMessageDialog(null, file.getName() + " is a good name for the file.");
                        askInputAndWrite(file);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter the name of the file.");
            }
        }
        while (Objects.requireNonNull(filename).trim().isEmpty());
    }

    private static void askInputAndWrite(File file) {
        Car[] carArray = new Car[2];

        // brand
        String[] brandArray = new String[2];

        // model
        String[] modelArray = new String[2];

        // year
        String[] yearArrayString = new String[2];
        int[] yearArrayInt = new int[2];

        // color
        String[] colorArray = new String[2];

        // mate
        String[] mateArrayString = new String[2];
        boolean[] mateArrayBoolean = new boolean[2];

        // data of the first car

        // brand of the first car
        brandArray [0] = JOptionPane.showInputDialog(null, "Enter the brand of the first car.");

        // model of the first car
        modelArray[0] = JOptionPane.showInputDialog(null, "Enter the model of the " + brandArray[0] + ".");

        // year of the first car
        yearArrayString[0] = JOptionPane.showInputDialog(null, "Enter the year of fabrication of the " + brandArray[0] + ".");
        yearArrayInt[0] = Integer.parseInt(yearArrayString[0]);

        // color of the first car
        colorArray [0] = JOptionPane.showInputDialog(null, "Enter the color of " + brandArray[0] + ".");

        // ask if the first car is mate
        mateArrayString[0] = JOptionPane.showInputDialog(null, "Enter 'true' if the color of the " + brandArray[0] + " is mate; 'false' if it is not.");
        if (mateArrayString[0].equalsIgnoreCase("true"))
            mateArrayBoolean[0] = true;

        // first car object
        carArray[0] = new Car(brandArray[0], modelArray[0], yearArrayInt[0], colorArray[0], mateArrayBoolean[0]);

        // data of the second car

        // brand of the second car
        brandArray[1] = JOptionPane.showInputDialog(null, "Enter the brand of the second car.");

        // model of the second car
        modelArray[1] = JOptionPane.showInputDialog(null, "Enter the model of the " + brandArray[1] + ".");

        // year of the second car
        yearArrayString[1] = JOptionPane.showInputDialog(null, "Enter the year of fabrication of the " + brandArray[1] + ".");
        yearArrayInt[1] = Integer.parseInt(yearArrayString[1]); // Update index to 1

        // color of the second car
        colorArray[1] = JOptionPane.showInputDialog(null, "Enter the color of the " + brandArray[1] + ".");

        // ask if the second car is mate
        mateArrayString[1] = JOptionPane.showInputDialog(null, "Enter 'true' if the color of the " + brandArray[1] + " is mate; 'false' if it is not.");
        if (mateArrayString[1].equalsIgnoreCase("true"))
            mateArrayBoolean[1] = true;

        // second car object
        carArray[1] = new Car(brandArray[1], modelArray[1], yearArrayInt[1], colorArray[1],mateArrayBoolean[1]);

        // write the objects to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Car car : carArray) {
                oos.writeObject(car);
            }

            JOptionPane.showMessageDialog(null, "The " + brandArray[0] + " and the " + brandArray[1] + " were successfully written to the file.");

            // method to read the content of the file
            readTheFile(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readTheFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                try {
                    Car car = (Car) ois.readObject();
                    JOptionPane.showMessageDialog(null, "Brand: " + car.getBrand() + ".\tModel: " + car.getModel() + ".\tColor: " + car.getColor() + ".\n");
                } catch (EOFException e) {
                    // End of file reached, break the loop
                    break;
                } catch (Exception ex) {
                    // Log any other exceptions for debugging
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file.", e);
        }
    }

}
