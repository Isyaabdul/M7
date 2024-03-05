import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Custom exception for empty file
class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        String fileName = "src/Biography.txt"; // Change this to the name of your file


        try {
            // Read the file and check if it's empty
            String fileContent = readFile(fileName);
            if (fileContent.isEmpty()) {
                throw new EmptyFileException("The file is empty: " + fileName);
            }

            // If the file is not empty, print its content
            System.out.println("File content:");
            System.out.println(fileContent);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to read the file content
    private static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder content = new StringBuilder();

        // Check if the file exists and is readable
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("File not found or not readable: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Read lines until the end of the file
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
