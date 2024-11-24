package pairmatching.model;

import static java.util.Arrays.asList;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pairmatching.util.FileDataLoader;

public class FrontendCrew {

    private final FileDataLoader fileDataLoader = new FileDataLoader();
    private final List<String> crew;

    public FrontendCrew() {
        this.crew = loadDataFromFile();
    }

    private List<String> loadDataFromFile() {
        List<String> crew = new ArrayList<>();

        try {
            crew = fileDataLoader.loadDataFromFile("frontend-crew.md");
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("[Error] " + e.getMessage());
        }
        return crew;
    }

    public Pair matchPairs(){
        List<String> shuffledCrew = Randoms.shuffle(crew);
        Map<String,List<String>> matchedPair = new LinkedHashMap<>();
        boolean odd = crew.size() % 2 != 0;

        if(odd){
            int i;
            for(i=0;i<crew.size()-3;i++){
                matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
                i++;
            }

            matchedPair.put(shuffledCrew.get(i),
                    asList(shuffledCrew.get(i + 1), shuffledCrew.get(i + 2)));

            return new Pair(matchedPair);
        }


        for(int i = 0; i<crew.size();i++){
            matchedPair.put(shuffledCrew.get(i), Collections.singletonList(shuffledCrew.get(i + 1)));
            i++;
        }

        return new Pair(matchedPair);
    }

    public void print(){
        for (String s : crew) {
            System.out.println(s);
        }
    }
}
