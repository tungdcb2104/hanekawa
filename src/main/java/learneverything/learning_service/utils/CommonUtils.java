package learneverything.learning_service.utils;


import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {
    private static final long maxFileSize = 10485760; // TODO: should use value in variable of properties file

    public static String saveFile(String uploadDir, String fileName, byte[] bytes) throws IOException {
        if (fileName == null || fileName.isBlank() || fileName.contains("..")) {
            throw new BaseException(Error.INVALID_FILE);
        }

        if (bytes.length > maxFileSize) {
            throw new BaseException(Error.PAYLOAD_TOO_LARGE);
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
