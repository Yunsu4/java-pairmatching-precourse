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


public class Crew {
    private Course course;
    private final List<String> crew;

    private final FileDataLoader fileDataLoader = new FileDataLoader();


    public Crew(Course enteredCourse) throws IllegalArgumentException{
        this.course = enteredCourse;
        this. crew = matchingCourse(enteredCourse);

    }

    private List<String> matchingCourse(Course enteredCourse) {
        if(enteredCourse == Course.BACKEND){
            this.course = Course.BACKEND;
            return loadDataFromFile("backend-crew.md");
        }
        this.course = Course.FRONTEND;
        return loadDataFromFile("frontend-crew.md");
    }

    private List<String> loadDataFromFile(String file) {
        List<String> crew = new ArrayList<>();

        try {
            crew = fileDataLoader.loadDataFromFile(file);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("[Error] " + e.getMessage());
        }
        return crew;
    }

    public Pairs matchPairs(){
        List<String> shuffledCrew = Randoms.shuffle(crew);
        Map<String,List<String>> matchedPair = new LinkedHashMap<>();

        boolean odd = shuffledCrew.size() % 2 != 0;

        if(odd){
            int i;
            for(i=0;i<shuffledCrew.size()-3;i++){
                matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
                i++;
            }
            matchedPair.put(shuffledCrew.get(i),
                    asList(shuffledCrew.get(i + 1), shuffledCrew.get(i + 2)));

            return new Pairs(matchedPair);
        }


        for(int i = 0; i<shuffledCrew.size();i++){
            matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
            i++;
        }

        return new Pairs(matchedPair);
    }
}
