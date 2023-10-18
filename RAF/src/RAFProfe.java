import java.io.*;
import java.util.Scanner;

public class RAFProfe {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in); // create the scanner object for user input

        // create file
        String path = System.getProperty("user.dir");
        String fileName = "actividad1_03_profe.dat";
        File file = new File(path + fileName);

        // reset the content of the file
        if (file.exists())
        {
            System.out.println("The file already exists, we will delete it and create it again to reset the content.");
        }
        if (file.delete())
        {
            System.out.println("The file has been deleted");
        }
        if (file.createNewFile())
        {
            System.out.println("The file was created successfully\n");
        }

        // write to the file
        writeFile(file, 0, 0);

        // count the times the file is read
        int counter = 0;
        // read the file
        counter ++;
        readFile(file, counter);
        // ask the user for a department to modify for the third employee
        int userDepartment;
        do {
            System.out.println("\nEnter the new department for the third employee, please.");
            userDepartment = scanner.nextInt();

            // calculate the position for the third record
            int recordSize = 4 + 10 * 2 + 4 + 8;
            int recordThirdPosition = 2 * recordSize;
            int departmentPosition = recordThirdPosition + 4 + 10 * 2;
            writeFile(file, departmentPosition, userDepartment);
        } while (userDepartment <= 0);

        // read the file after writing the new department
        counter ++;
        readFile(file, counter);

    }
    private static void writeFile(File file, int position, int userDepartment) {
        // variables
        int[] idArray = {1, 2, 3, 4}; // array of ids
        String[] namesArray = {"John", "Johnny", "Johnson", "Johnnyson"}; // array of names
        int[] departmentsArray = {10, 20, 30, 40}; // array of departments
        double[] salaryArray = {1000.5, 2000.5, 3000.5, 4000.5}; // array of salaries

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw"))
        {
            if (position > 0)
            {
                raf.seek(position);
                raf.writeInt(userDepartment);
            } else if (position == 0)
            {
                raf.seek(0);
                // create the StringBuilder to set the length of the strings
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < namesArray.length; i++)
                {
                    sb.setLength(0); // Reset the StringBuilder
                    sb.append(namesArray[i]);
                    sb.setLength(10); // Set the length to 10 characters

                    raf.writeInt(idArray[i]);
                    raf.writeChars(sb.toString());
                    raf.writeInt(departmentsArray[i]);
                    raf.writeDouble(salaryArray[i]);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static void readFile (File file, int counter)
    {
        double salary = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r"))
        {
            raf.seek(0); // pointer to the beginning of the file
            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                StringBuilder nameBuilder = new StringBuilder(10);
                for (int i = 0; i < 10; i++)
                {
                    nameBuilder.append(raf.readChar());
                }
                int department = raf.readInt();
                salary = raf.readDouble();

                // Process the read data (you can print or do whatever you need)
                System.out.println("ID: " + id + ", Name: " + nameBuilder.toString().trim() +      ", Department: " + department + ", Salary: " + salary);
            }
            if (counter == 2)
            {
                salary += salary;
                System.out.println("\nTotal salary = " + salary);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
