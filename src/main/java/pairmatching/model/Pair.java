package pairmatching.model;

import java.util.List;
import java.util.Map;

public class Pair {

    private Map<String, List<String>> pair;

    public Pair(Map<String, List<String>> pair){
        this.pair = pair;
    }

    public void display() {
        for (String key : pair.keySet()) {
            String value = null;
            if(pair.get(key).size() == 2){
                value = pair.get(key).get(0);
                value += " : ";
                value += pair.get(key).get(1);
            }
            if(pair.get(key).size() == 1){
                value = String.valueOf(pair.get(key).get(0));
            }
            System.out.println(key+" : "+ value);
        }
    }
}
