package actividad1_10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyAndPasteImage {
    public static void main(String[] args) {

        // source directory
        String sourcePath = System.getProperty("user.home");
        File sourceDir = new File(sourcePath, "Downloads");

        // create a file object for an image file in the download directory
        String imageName = "javaImage.png";
        File imageFile = new File(sourceDir, imageName);

        // go to the destination folder: documents
        String destinationPath = System.getProperty("user.home");
        File destinationDir = new File(destinationPath, "Documents");

        // create the new folder inside Documents
        File destinationFolder = new File (destinationDir, "copy");

            try
            {
                if (destinationFolder.mkdir())
                {
                    System.out.println("Destination folder created");
                }
                else
                {
                    System.out.println("The destination folder already exists");
                }

                // Specify the destination file path
                Path destinationFilePath = destinationFolder.toPath().resolve(imageFile.getName());

                // Copy the image file to the destination folder
                Files.copy(imageFile.toPath(), destinationFilePath);
                System.out.println("Image copied to " + destinationFolder.getName());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
