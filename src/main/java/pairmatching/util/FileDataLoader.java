package pairmatching.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import pairmatching.view.error.ErrorException;
import pairmatching.view.error.InputErrorType;

public class FileDataLoader {
    public static final String FILE_IS_EMPTY = "파일이 비어있습니다.";
    public static final String FILE_PATH_DOES_NOT_EXIST_IN_RESOURCES = "파일 경로가 존재하지 않습니다.";

    public List<String> loadDataFromFile(String fileName) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        Scanner fileData = readFile(resource);

        return extractFields(fileData);
    }

    private Scanner readFile(URL resource) throws FileNotFoundException {
        checkFilePathAvailable(resource);
        File file = new File(resource.getFile());
        checkFileEmpty(file);
        return new Scanner(file);
    }

    private void checkFileEmpty(File file) {
        if (file.length() == 0) {
            throw new ErrorException(FILE_IS_EMPTY);
        }
    }

    private void checkFilePathAvailable(URL resource) throws FileNotFoundException {
        if (resource == null) {
            throw new FileNotFoundException(
                    InputErrorType.ERROR_MESSAGE.getMessage() + FILE_PATH_DOES_NOT_EXIST_IN_RESOURCES);
        }
    }

    private List<String> extractFields(Scanner fileData) {
        List<String> values = new ArrayList<>();

        while (fileData.hasNextLine()) {
            values.add(fileData.nextLine());
        }
        return values;
    }
}
