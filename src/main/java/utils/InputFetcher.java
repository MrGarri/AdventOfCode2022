package utils;

import lombok.experimental.UtilityClass;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@UtilityClass
public class InputFetcher {

    public List<String> fetchMainInput(String resourceFolder) {
        Path resourceFolderPath = FileUtils.getPathFromResources(resourceFolder + "/main-input.txt");
        try {
            return FileUtils.readFileLines(resourceFolderPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> fetchExampleInput(String resourceFolder) {
        Path resourceFolderPath = FileUtils.getPathFromResources(resourceFolder + "/example-input.txt");
        try {
            return FileUtils.readFileLines(resourceFolderPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
