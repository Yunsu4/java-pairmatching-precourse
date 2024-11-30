package pairmatching.controller;

import java.util.function.Function;
import org.mockito.internal.util.Supplier;
import pairmatching.enums.Course;
import pairmatching.enums.Crew;
import pairmatching.enums.Functions;
import pairmatching.enums.Level;
import pairmatching.enums.Missions;
import pairmatching.enums.ShortAnswer;
import pairmatching.model.MatchingRecord;
import pairmatching.model.Pairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.error.ErrorException;

public class PairMatchingController {

    private static final String COMMA = "\\s*,\\s*";
    private static final String EXIT = "exit";
    public static final int MAX_TRY_COUNT = 3;

    private InputView inputView;
    private OutputView outputView;
    private Crew backend;
    private Crew frontend;
    private MatchingRecord backendRecord;
    private MatchingRecord frontendRecord;


    public PairMatchingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.backend = new Crew(Course.BACKEND);
        this.frontend = new Crew(Course.FRONTEND);
        this.backendRecord = new MatchingRecord();
        this.frontendRecord = new MatchingRecord();
    }

    public void matchingSystemRun() {
        while (true) {
            try {
                String input = getValidInput(inputView::displayStartMessage, Functions::getValidFunction);
                executeWithInput(input);
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals(EXIT)) {
                    return;
                }
                System.out.println(e.getMessage());
            }
        }
    }

    private void executeWithInput(String input) throws IllegalArgumentException {
        if (Functions.isEquals(input, Functions.QUIT)) {
            performFunctions(input, inputView);
        }
        if (!Functions.isEquals(input, Functions.QUIT)) {
            throw new IllegalArgumentException(EXIT);
        }
    }

    private void performFunctions(String input, InputView inputView) throws ErrorException {
        executePairMatching(input, inputView);
        executePairInquiry(input, inputView);
        executePairInitialization(input);
    }

    private void executePairMatching(String input, InputView inputView) throws ErrorException {
        if (Functions.isEquals(input, Functions.MATCH_PAIRS)) {
            String[] matchingCriteria = parseInput(inputView);
            String course = Course.isCourse(matchingCriteria[0]);
            Level level = Level.getLevel(matchingCriteria[1]);
            Missions mission = Missions.getValidMission(level, matchingCriteria[2]);

            matchDistinctPairs(course, mission);
        }
    }

    private void executePairInquiry(String input, InputView inputView) throws ErrorException {
        if (Functions.isEquals(input, Functions.PAIR_INQUIRY)) {
            String[] matchingCriteria = parseInput(inputView);

            String course = Course.isCourse(matchingCriteria[0]);
            Level level = Level.getLevel(matchingCriteria[1]);
            Missions mission = Missions.getValidMission(level, matchingCriteria[2]);
            MatchingRecord record = getRecordThroughCourse(course);
            Pairs matchedPairs = record.getRecordThroughMission(mission);
            matchedPairs.display();
        }
    }

    private void executePairInitialization(String input) {
        if (Functions.isEquals(input, Functions.INITIALIZE_PAIR)) {
            backendRecord.clear();
            frontendRecord.clear();
            outputView.displayReset();
        }
    }

    private String[] parseInput(InputView inputView) {
        String matchingCriteria = inputView.enteredChoices();
        return matchingCriteria.split(COMMA);
    }

    private void matchDistinctPairs(String course, Missions mission) {
        MatchingRecord record = getRecordThroughCourse(course);
        if (record.containPreviousMatching(mission)) {
            rematching(course, mission, record);
            return;
        }
        handleNewMatching(course, mission, record);
    }

    private void rematching(String course, Missions mission, MatchingRecord record) {
        String input = getValidInput(inputView::enteredRematching, ShortAnswer::getValidAnswer);
        if (ShortAnswer.isEquals(input, ShortAnswer.YES)) {
            Pairs matchedPairs = matchingPairs(course);
            record.replace(mission, matchedPairs);
        }
    }

    private void handleNewMatching(String course, Missions mission, MatchingRecord record) {
        int count = 0;
        while (true) {
            Pairs matchedPairs = matchingPairs(course);
            if (record.containTheSamePairInTheSameLevel(mission, matchedPairs)) {
                count = handleRetry(count);
                continue;
            }
            record.add(mission, matchedPairs);
            break;
        }
    }

    private int handleRetry(int count) {
        count++;
        overTryCount(count);
        return count;
    }

    private void overTryCount(int count) throws ErrorException {
        if (count >= MAX_TRY_COUNT) {
            throw new ErrorException("매칭 시도 횟수가 3회를 초과했습니다.");
        }
    }

    private Pairs matchingPairs(String course) {
        Crew crew = getCrewThroughCourse(course);
        Pairs matchedPairs = crew.matchPairs();
        displayMatchedPairs(matchedPairs);
        return matchedPairs;
    }

    private Crew getCrewThroughCourse(String course) {
        if (Course.isEquals(course, Course.BACKEND)) {
            return backend;
        }
        return frontend;
    }

    private MatchingRecord getRecordThroughCourse(String course) {
        if (Course.isEquals(course, Course.BACKEND)) {
            return backendRecord;
        }
        return frontendRecord;
    }

    private void displayMatchedPairs(Pairs matchedPairs) {
        outputView.displayPairMatchingResult();
        matchedPairs.display();
    }

    private <T> T getValidInput(Supplier<String> inputSupplier, Function<String, T> converter) {
        while (true) {
            String input = inputSupplier.get();
            try {
                return converter.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
