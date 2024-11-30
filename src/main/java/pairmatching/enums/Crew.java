package pairmatching.enums;

import static java.util.Arrays.asList;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pairmatching.model.Pairs;
import pairmatching.util.FileDataLoader;
import pairmatching.view.error.ErrorException;


public class Crew {
    private static final int SIZE_OF_BASIC_PAIR_MATCHING = 2;
    private static final int SIZE_OF_SPECIFIC_PAIR_MATCHING = 3;
    private static final String FILE_RESOURCE_OF_BACKEND_CREW = "backend-crew.md";
    private static final String FILE_RESOURCE_OF_FRONTEND_CREW = "frontend-crew.md";
    private final List<String> crew;

    private final FileDataLoader fileDataLoader = new FileDataLoader();


    public Crew(Course enteredCourse) throws IllegalArgumentException {
        this.crew = matchingCourse(enteredCourse);

    }

    private List<String> matchingCourse(Course enteredCourse) {
        if (enteredCourse == Course.BACKEND) {
            return loadDataFromFile(FILE_RESOURCE_OF_BACKEND_CREW);
        }
        return loadDataFromFile(FILE_RESOURCE_OF_FRONTEND_CREW);
    }

    private List<String> loadDataFromFile(String file) {
        List<String> crew = new ArrayList<>();

        try {
            crew = fileDataLoader.loadDataFromFile(file);
        } catch (FileNotFoundException | ErrorException e) {
            System.err.println(e.getMessage());
        }
        return crew;
    }

    public Pairs matchPairs() {
        List<String> shuffledCrew = Randoms.shuffle(crew);
        Map<String, List<String>> matchedPair = new LinkedHashMap<>();

        boolean odd = shuffledCrew.size() % SIZE_OF_BASIC_PAIR_MATCHING != 0;

        if (odd) {
            int i;
            for (i = 0; i < shuffledCrew.size() - SIZE_OF_SPECIFIC_PAIR_MATCHING; i++) {
                matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
                i++;
            }
            matchedPair.put(shuffledCrew.get(i),
                    asList(shuffledCrew.get(i + 1), shuffledCrew.get(i + 2)));

            return new Pairs(matchedPair);
        }

        for (int i = 0; i < shuffledCrew.size(); i++) {
            matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
            i++;
        }

        return new Pairs(matchedPair);
    }
}
