package RAFBook;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class actividad1_03
{
    public static void main(String[] args)
    {
        // creating the file
        String path = System.getProperty("user.dir");
        File file = new File(path + File.separator + "actividad1_03.dat");

        // storing some employees in the employees list
        Employee employee01 = new Employee(1, "john", 10, 2000);
        Employee employee02 = new Employee(2, "johnny", 20, 3000);
        Employee employee03 = new Employee(3, "johnnyson", 30, 4000);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee01);
        employeeList.add(employee02);
        employeeList.add(employee03);

        // ask the user for the id of an employee
        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id for the employee you want to get the data of."));


        // write the list to the dat file
        try (RandomAccessFile rw = new RandomAccessFile(file, "rw"))
        {
            rw.seek(0);
            for (Employee emp : employeeList)
            {
                rw.writeInt(emp.id());
                rw.writeUTF(emp.surname());
                rw.writeInt(emp.department());
                rw.writeDouble(emp.salary());
            }

            // show the employee from the list
            boolean idFound = false;
            for (Employee emp : employeeList)
            {
                if (emp.id() == userInput)
                {
                    JOptionPane.showMessageDialog(null, emp.id()+ "\n" + emp.surname() + "\n" + emp.department() + "\n" + emp.salary());
                    idFound = true;
                    break;
                }
            }

            if (!idFound)
            {
                JOptionPane.showMessageDialog(null, "The id entered was not found");
            }

            // show the data from the file --- don't know how
            JOptionPane.showMessageDialog(null, "Showing the data from the file.");
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String [] parts = line.split("\\s+");
                    int currentId = Integer.parseInt(parts[0]);

                    if (currentId == userInput)
                    {
                        JOptionPane.showMessageDialog(null, line);
                        break;
                    }
                }
                if (line == null) {
                    System.out.println("ID " + userInput + " not found in the file.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}