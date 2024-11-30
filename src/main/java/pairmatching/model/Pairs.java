package pairmatching.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pairs {

    private Map<String, List<String>> pairs;

    public Pairs(Map<String, List<String>> pair){
        this.pairs = pair;
    }

    public Set<Map.Entry<String, List<String>>> entrySet(){
        return pairs.entrySet();
    }

    public void display() {
        for (String key : pairs.keySet()) {
            String value = null;
            if(pairs.get(key).size() == 2){
                value = pairs.get(key).get(0);
                value += " : ";
                value += pairs.get(key).get(1);
            }
            if(pairs.get(key).size() == 1){
                value = String.valueOf(pairs.get(key).get(0));
            }
            System.out.println(key+" : "+ value);
        }
    }

}
