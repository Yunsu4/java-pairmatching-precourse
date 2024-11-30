package pairmatching.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.enums.Missions;
import pairmatching.view.error.ErrorException;

public class MatchingRecord {

    private Map<Missions, Pairs> matchingRecord;

    public MatchingRecord() {
        this.matchingRecord = new HashMap<>();
    }

    public void add(Missions mission, Pairs matchedPairs) {
        matchingRecord.put(mission, matchedPairs);
    }

    public void replace(Missions mission, Pairs pairs) {
        matchingRecord.put(mission, pairs);
    }

    public void clear() {
        matchingRecord = null;
    }

    public Pairs getRecordThroughMission(Missions mission) {
        return matchingRecord.entrySet().stream()
                .filter(entry -> entry.getKey() == mission)
                .map(Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new ErrorException("해당 미션에 대한 기록이 없습니다."));
    }

    public boolean containPreviousMatching(Missions mission) {
        return matchingRecord.entrySet().stream()
                .anyMatch(entry -> entry.getKey() == mission);
    }

    public boolean containTheSamePairInTheSameLevel(Missions mission, Pairs matchedPairs) {
        return matchingRecord.entrySet().stream()
                .filter(entry -> entry.getKey().getLevel().equals(mission.getLevel()))
                .anyMatch(entry -> hasSameSet(entry.getValue(), matchedPairs));
    }

    public boolean hasSameSet(Pairs thisPairs, Pairs otherPairs) {
        List<Set<String>> thisSets = convertPairsToSetList(thisPairs);
        List<Set<String>> otherSets = convertPairsToSetList(otherPairs);

        return thisSets.stream().anyMatch(otherSets::contains);
    }


    private List<Set<String>> convertPairsToSetList(Pairs pairs) {
        return pairs.entrySet().stream()
                .map(entry -> createKeyValueSet(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private Set<String> createKeyValueSet(String key, List<String> values) {
        Set<String> combined = new HashSet<>();
        combined.add(key);
        combined.addAll(values);
        return combined;
    }
}
