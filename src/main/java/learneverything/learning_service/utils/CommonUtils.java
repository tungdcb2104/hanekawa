package learneverything.learning_service.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {
    @Value("${app.max-file-size-mb}")
    private static int maxFileSizeMb;

    public static String saveFile(String uploadDir, String fileName, byte[] bytes) throws IOException {
        if (fileName == null || fileName.isBlank() || fileName.contains("..")) {
            throw new IOException("Invalid file name provided.");
        }

        if (bytes.length > maxFileSizeMb * 1024 * 1024) {
            throw new IOException("File size exceeds the maximum limit of " + maxFileSizeMb + " MB.");
        }

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, bytes);

        return filePath.toString();
    }
}
