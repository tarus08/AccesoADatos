import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;

public class actividad1_13 {

    public static void main(String[] args) throws IOException {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel fileNameLabel = new JLabel("Filename: ");
        fileNameLabel.setBounds(10, 20, 80, 25);
        panel.add(fileNameLabel);

        JTextField userFileName = new JTextField(20);
        userFileName.setBounds(80, 25, 260, 20);
        panel.add(userFileName);

        JLabel writeSomeText = new JLabel("Write some text:");
        writeSomeText.setBounds(10, 80, 100, 20);
        panel.add(writeSomeText);

        JTextField textToWrite = new JTextField(50);
        textToWrite.setBounds(10, 115, 330, 60);
        panel.add(textToWrite);

        JLabel textContentOfTheFile = new JLabel("Content of the file:");
        textContentOfTheFile.setBounds(10, 210, 130, 20);
        panel.add(textContentOfTheFile);

        JTextArea contentFile = new JTextArea();
        contentFile.setEditable(false);
        contentFile.setBounds(10, 240, 330, 60);
        panel.add(contentFile);

        // create the file
        String path = System.getProperty("user.dir");
        String fileName = userFileName.getText();
        if (!fileName.contains(".txt")) {
            fileName += ".txt";
        }

        File file = new File(path + File.separator + fileName);
        if (file.exists())
            // delete the content of the file each time the program is run
            file.delete();
        file.createNewFile();

        frame.setVisible(true);

        JButton overwriteButton = createButton("Overwrite", 20, 90, e -> {
            try {
                try (RandomAccessFile rw = new RandomAccessFile(file, "rw")) {
                    rw.seek(0);
                    rw.writeUTF(textToWrite.getText());
                    showMessageDialog("The content was successfully overwritten to the file.");
                }
            } catch (IOException ex) {
                showMessageDialog("Error: " + ex.getMessage());
            }
        });
        panel.add(overwriteButton);

        JButton addAtTheEndButton = createButton("Add at the end", 130, 120, e ->
        {
            try {
                try (RandomAccessFile rw = new RandomAccessFile(file, "rw")) {
                    rw.seek(file.length() - 1);
                    rw.writeUTF(textToWrite.getText());
                    showMessageDialog("The content was successfully written to the file.");
                }
            } catch (IOException ex) {
                showMessageDialog("Error: " + ex.getMessage());
            }
        });
        panel.add(addAtTheEndButton);

        JButton showContentButton = createButton("Show content", 270, 120, e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder readContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    readContent.append(line).append("\n");
                }
                contentFile.setText(readContent.toString());
                showMessageDialog("Content loaded from the file.");
            } catch (IOException ex) {
                showMessageDialog("Error: " + ex.getMessage());
            }
        });

        panel.add(showContentButton);
    }
    private static JButton createButton(String label, int x, int width, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setBounds(x, 340, width, 20);
        button.addActionListener(actionListener);
        return button;
    }

    private static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}