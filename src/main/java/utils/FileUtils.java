package utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class FileUtils {

    public List<String> readFileLines(@NonNull final Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }

    public Path getPathFromResources(@NonNull final String fileName) {
        return Paths.get("src/main/resources/", fileName);
    }

}
