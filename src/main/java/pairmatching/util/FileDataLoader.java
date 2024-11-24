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

public class FileDataLoader {
    public static final String FILE_IS_EMPTY = "File is empty: ";
    public static final String FILE_PATH_DOES_NOT_EXIST_IN_RESOURCES = "File path does not exist in resources";
    public static final String FAILED_TO_CREATE_INSTANCE_OF = "Failed to create instance of";

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
            throw new IllegalArgumentException(FILE_IS_EMPTY + file.getPath());
        }
    }

    private void checkFilePathAvailable(URL resource) throws FileNotFoundException {
        if (resource ==null) {
            throw new FileNotFoundException(FILE_PATH_DOES_NOT_EXIST_IN_RESOURCES);
        }
    }

    private List<String> extractFields(Scanner fileData) {
        List<String> values= new ArrayList<>();

        while (fileData.hasNextLine()) {
            values.add(fileData.nextLine());
        }
        return values;
    }

    private <T> List<T> extractValues(Scanner fileData, String[] productFields, Class<T> type) {
        List<T> items = new ArrayList<>();

        while (fileData.hasNextLine()) {
            String line = fileData.nextLine();
            String[] values = line.split(",", -1);

            Map<String, Object> itemData = matchValuesWithFields(productFields, values);
            T item = createInstance(type, itemData);
            items.add(item);
        }
        fileData.close();

        return items;

    }

    private Map<String, Object> matchValuesWithFields(String[] productFields, String[] values) {
        Map<String, Object> product = new LinkedHashMap<>();
        for(int i = 0; i< productFields.length; i++){
            String key = productFields[i];
            String value = values[i];
            product.put(key, value);
        }
        return product;
    }

    private <T> T createInstance(Class<T> type, Map<String, Object> data){
        try{
            return type.getDeclaredConstructor(Map.class).newInstance(data);
        }catch (Exception e){
            throw new RuntimeException(FAILED_TO_CREATE_INSTANCE_OF + type.getName(), e);
        }
    }
}
